package net.tntmaster.tntmod.block.entity;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.tntmaster.tntmod.block.custom.TapePlayerBlock;
import net.tntmaster.tntmod.item.custom.TapeItem;
import net.tntmaster.tntmod.networking.ModPackets;
import net.tntmaster.tntmod.networking.packet.PlayTapeS2CPacket;
import net.tntmaster.tntmod.networking.packet.StopTapeS2CPacket;
import net.tntmaster.tntmod.sound.ModSounds;
import net.tntmaster.tntmod.util.ModTags;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class TapePlayerBlockEntity extends BlockEntity implements Clearable, ContainerSingleItem {
    private final NonNullList<ItemStack> items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    private int ticksSinceLastEvent;
    private long tickCount;
    private long tapeStartedTick;
    private boolean isPlaying;

    public TapePlayerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TAPE_PLAYER_BE.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("TapeItem", 10)) {
            this.items.set(0, ItemStack.of(pTag.getCompound("TapeItem")));
        }
        this.isPlaying = pTag.getBoolean("isPlaying");
        this.tapeStartedTick = pTag.getLong("TapeStartTick");
        this.tickCount = pTag.getLong("TickCount");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!this.getFirstItem().isEmpty()) {
            pTag.put("TapeItem", this.getFirstItem().save(new CompoundTag()));
        }

        pTag.putBoolean("IsPlaying", this.isPlaying);
        pTag.putLong("TapeStartTick", this.tapeStartedTick);
        pTag.putLong("TickCount", this.tickCount);
    }

    public boolean isTapePlaying() {
        return !this.getFirstItem().isEmpty() && this.isPlaying;
    }

    private void setHasTapeBlockState(@Nullable Entity pEntity, boolean pHasTape) {
        if (this.level.getBlockState(this.getBlockPos()) == this.getBlockState()) {
            this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(TapePlayerBlock.HAS_TAPE, Boolean.valueOf(pHasTape)), 2);
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(pEntity, this.getBlockState()));
        }
    }

    @VisibleForTesting
    public void startPlaying() {
        List<ServerPlayer> players = this.getLevel().getServer().getPlayerList().getPlayers();
        ItemStack itemStack = this.getFirstItem();
        this.level.playSound(null, this.getBlockPos().getCenter().x, this.getBlockPos().getCenter().y, this.getBlockPos().getCenter().z, ModSounds.TAPE_INSERT.get(), SoundSource.BLOCKS, 1, 1);
        this.tapeStartedTick = this.tickCount;
        this.isPlaying = true;
        for(ServerPlayer player : players) {
            if (Math.abs(player.getX() - this.getBlockPos().getX()) <= 64 && Math.abs(player.getY() - this.getBlockPos().getY()) <= 64 && Math.abs(player.getZ() - getBlockPos().getZ()) <= 64)
                ModPackets.sendToPlayer(new PlayTapeS2CPacket(this.getBlockPos(), itemStack), player);
        }
        this.setChanged();
    }

    private void stopPlaying() {
        List<ServerPlayer> players = this.getLevel().getServer().getPlayerList().getPlayers();

        this.isPlaying = false;
        for(ServerPlayer player : players) {
            ModPackets.sendToPlayer(new StopTapeS2CPacket(this.getBlockPos()), player);
        }

        this.setChanged();
    }

    private void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        ++this.ticksSinceLastEvent;
        if (this.isTapePlaying()) {
            Item item = this.getFirstItem().getItem();
            if (item instanceof TapeItem) {
                TapeItem tapeItem = (TapeItem) item;
                if (this.shouldTapeStopPlaying(tapeItem)) {
                    this.stopPlaying();
                } else if (this.shouldSendTapePlayerPlayingEvent()) {
                    this.ticksSinceLastEvent = 0;
                }
            }
        }
        ++this.tickCount;
    }

    private boolean shouldTapeStopPlaying(TapeItem pTape) {
        return this.tickCount >= this.tapeStartedTick + (long) pTape.getLengthInTicks() + 20L;
    }

    private boolean shouldSendTapePlayerPlayingEvent() {
        return this.ticksSinceLastEvent >= 20;
    }


    @Override
    public ItemStack getItem(int pSlot) {
        return this.items.get(pSlot);
    }

    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        ItemStack itemStack = Objects.requireNonNullElse(this.items.get(pSlot), ItemStack.EMPTY);
        this.items.set(pSlot, ItemStack.EMPTY);
        if (!itemStack.isEmpty()) {
            this.setHasTapeBlockState((Entity) null, false);
            this.stopPlaying();
        }

        return itemStack;
    }

    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        if (pStack.is(ModTags.Items.TAPE_ITEM) && this.level != null) {
            this.items.set(pSlot, pStack);
            this.setHasTapeBlockState((Entity) null, true);
            this.startPlaying();
        }

    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return Container.stillValidBlockEntity(this, pPlayer);
    }

    @Override
    public boolean canPlaceItem(int pIndex, ItemStack pStack) {
        return pStack.is(ModTags.Items.TAPE_ITEM) && this.getItem(pIndex).isEmpty();
    }

    @Override
    public boolean canTakeItem(Container pTarget, int pIndex, ItemStack pStack) {
        return pTarget.hasAnyMatching(ItemStack::isEmpty);
    }

    public void breakTapePlayer() {
        if (this.level != null && !this.level.isClientSide) {
            BlockPos blockPos = this.getBlockPos();
            ItemStack itemStack = this.getFirstItem();
            if (!itemStack.isEmpty()) {
                this.removeFirstItem();
                Vec3 vec3 = Vec3.atLowerCornerWithOffset(blockPos, 0.5D, 0.4D, 0.5D).offsetRandom(this.level.random, 0.7F);
                ItemStack itemstack1 = itemStack.copy();
                ItemEntity itementity = new ItemEntity(this.level, vec3.x(), vec3.y(), vec3.z(), itemstack1);
                itementity.setDefaultPickUpDelay();
                this.level.addFreshEntity(itementity);

            }
        }
    }

    public void removeTape(Player pPlayer) {
        if (this.level != null && !this.level.isClientSide && pPlayer != null) {
            ItemStack itemStack = this.getFirstItem();

            if (!(itemStack.isEmpty()) && itemStack.getItem() instanceof TapeItem) {
                ItemStack tape = itemStack.copy();
                Inventory pInventory = pPlayer.getInventory();

                this.removeFirstItem();
                this.stopPlaying();

                if (pPlayer.getMainHandItem().isEmpty()) {
                    pPlayer.setItemInHand(InteractionHand.MAIN_HAND, tape);
                } else {
                    pInventory.placeItemBackInInventory(tape);
                }

                this.level.playSound(null, this.getBlockPos(), ModSounds.TAPE_EJECT.get(), SoundSource.BLOCKS);
                pPlayer.inventoryMenu.broadcastChanges();
                this.setChanged();
            }
        }
    }

    public static void playTapeTick(Level pLevel, BlockPos pPos, BlockState pState, TapePlayerBlockEntity pTapePlayer) {
        pTapePlayer.tick(pLevel, pPos, pState);
    }

}
