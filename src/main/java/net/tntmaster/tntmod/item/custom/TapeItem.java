package net.tntmaster.tntmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tntmaster.tntmod.block.ModBlocks;
import net.tntmaster.tntmod.block.custom.TapePlayerBlock;
import net.tntmaster.tntmod.block.entity.TapePlayerBlockEntity;

import javax.annotation.Nullable;

public class TapeItem extends Item {
    private final int lengthInTicks;
    private final java.util.function.Supplier<SoundEvent> soundSupplier;

    public TapeItem(java.util.function.Supplier<SoundEvent> soundSupplier, Item.Properties builder, int lengthInTicks)
    {
        super(builder);
        this.soundSupplier = soundSupplier;
        this.lengthInTicks = lengthInTicks;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (blockstate.is(ModBlocks.TAPE_PLAYER.get()) && !blockstate.getValue(TapePlayerBlock.HAS_TAPE)) {
            ItemStack itemStack = pContext.getItemInHand();
            if (!level.isClientSide) {
                Player player = pContext.getPlayer();
                BlockEntity blockEntity = level.getBlockEntity(blockpos);
                if (blockEntity instanceof TapePlayerBlockEntity) {
                    TapePlayerBlockEntity tapePlayerBlockEntity = (TapePlayerBlockEntity) blockEntity;
                    tapePlayerBlockEntity.setFirstItem(itemStack.copy());
                }
                itemStack.shrink(1);

            }
            return InteractionResult.sidedSuccess(level.isClientSide);

        } else {
            return InteractionResult.PASS;
        }
    }


    public SoundEvent getSound() {
        return this.soundSupplier.get();
    }

    public int getLengthInTicks() {
        return this.lengthInTicks;
    }
}

