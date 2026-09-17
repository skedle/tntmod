package net.tntmaster.tntmod.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.tntmaster.tntmod.block.entity.TapePlayerBlockEntity;
import net.tntmaster.tntmod.item.custom.TapeItem;

import java.util.function.Supplier;

public class RemoveTapeC2SPacket {
    private final BlockPos pos;
    private final ItemStack itemStack;

    public RemoveTapeC2SPacket(BlockPos pPos, ItemStack itemStack) {
        this.pos = pPos;
        this.itemStack = itemStack;
    }
    public RemoveTapeC2SPacket(FriendlyByteBuf buf) {
         pos = buf.readBlockPos();
         itemStack = buf.readItem();

    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeItem(itemStack);

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer pPlayer = context.getSender();

            if (pPlayer == null) {
                return;
            }
            ServerLevel level = pPlayer.serverLevel().getLevel();
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (!(blockEntity instanceof TapePlayerBlockEntity)) {
                return;
            }
            ItemStack tape = itemStack.copy();

            if (!(Item.byId(Item.getId(tape.getItem())) instanceof TapeItem)) {
                return;
            }
            Inventory pInventory = pPlayer.getInventory();

            if(pPlayer.getMainHandItem().isEmpty()) {
                pPlayer.setItemInHand(InteractionHand.MAIN_HAND, tape);
            }
            else {
                int freeSlot = pInventory.getFreeSlot();
                if (freeSlot == -1) {
                    pPlayer.drop(tape, false);
                    return;
                }
                pInventory.setItem(freeSlot, tape);
            }
            pPlayer.inventoryMenu.broadcastChanges();
        });
        return true;
    }

}