package net.tntmaster.tntmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.tntmaster.tntmod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Supplier;

public class JaronaFlowerPotBlock extends FlowerPotBlock {
    public static final BooleanProperty HAVE_FACE = BooleanProperty.create("have_face_pot");


    public JaronaFlowerPotBlock(@Nullable Supplier<FlowerPotBlock> emptyPot, Supplier<? extends Block> pContent, Properties properties) {
        super(emptyPot, pContent, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HAVE_FACE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HAVE_FACE);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pState.getValue(HAVE_FACE))
            pLevel.setBlockAndUpdate(pPos, pState.setValue(HAVE_FACE, true));
        Random r = new Random();
        Integer r1 = r.nextInt(100);
        if (r1 == 99) {
            pLevel.playSound(null, pPos.getCenter().x, pPos.getCenter().y, pPos.getCenter().z, ModSounds.JA_ORANGE.get(), SoundSource.MASTER, 1, 1);
        }
        else {
            pLevel.playSound(null, pPos.getCenter().x, pPos.getCenter().y, pPos.getCenter().z, ModSounds.JARONA.get(), SoundSource.MASTER, 0.5f, 1);

        }
        r1 = null;
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getValue(HAVE_FACE))
            pLevel.playSound(null, pPos.getCenter().x, pPos.getCenter().y, pPos.getCenter().z, ModSounds.FLOWERY_GOODBYE.get(), SoundSource.MASTER, 1, 1);

    }
}
