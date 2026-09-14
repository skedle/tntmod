package net.tntmaster.tntmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tntmaster.tntmod.block.entity.ModBlockEntities;
import net.tntmaster.tntmod.block.entity.TapePlayerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class TapePlayerBlock extends BaseEntityBlock {
    public static final BooleanProperty HAS_TAPE = BooleanProperty.create("has_tape");
    public static final VoxelShape SHAPE = Block.box(4,0,2, 12, 2.5, 14);

    public TapePlayerBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_TAPE, Boolean.valueOf(false)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HAS_TAPE);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        CompoundTag compoundTag = BlockItem.getBlockEntityData(pStack);
        if (compoundTag != null && compoundTag.contains("TapeItem")) {
            pLevel.setBlock(pPos, pState.setValue(HAS_TAPE, Boolean.valueOf(true)), 2);
        }
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pState.getValue(HAS_TAPE)) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof TapePlayerBlockEntity) {
                TapePlayerBlockEntity tapePlayerBlockEntity = (TapePlayerBlockEntity) blockEntity;
                tapePlayerBlockEntity.removeTape();
                return InteractionResult.sidedSuccess(pLevel.isClientSide);
            }
        }

        return InteractionResult.PASS;

    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof TapePlayerBlockEntity) {
                TapePlayerBlockEntity tapePlayerBlockEntity = (TapePlayerBlockEntity) blockEntity;
                tapePlayerBlockEntity.removeTape();
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TapePlayerBlockEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pState.getValue(HAS_TAPE) ? createTickerHelper(pBlockEntityType, ModBlockEntities.TAPE_PLAYER_BE.get(), TapePlayerBlockEntity::playTapeTick) : null;
}
}
