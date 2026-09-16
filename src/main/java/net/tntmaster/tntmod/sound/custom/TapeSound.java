package net.tntmaster.tntmod.sound.custom;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.Vec3;
import net.tntmaster.tntmod.item.custom.TapeItem;

import java.util.Map;

public class TapeSound{
    private final Map<BlockPos, SoundInstance> playingTapes = Maps.newHashMap();
    private final Minecraft minecraft = Minecraft.getInstance();
    private static final TapeSound INSTANCE = new TapeSound();

    private TapeSound() {}

    public static TapeSound getInstance() {
        return INSTANCE;
    }

    public void playTape(BlockPos pPos, TapeItem tapeItem) {
        SoundInstance soundInstance = this.playingTapes.get(pPos);
        SoundEvent pSoundEvent = tapeItem.getSound();
        if (soundInstance != null) {
            minecraft.getSoundManager().stop(soundInstance);
            this.playingTapes.remove(pPos);

        }
        SoundInstance simpleSoundInstance = SimpleSoundInstance.forRecord(pSoundEvent, Vec3.atCenterOf(pPos));
        this.playingTapes.put(pPos, simpleSoundInstance);
        minecraft.getSoundManager().play(simpleSoundInstance);

    }

    public void stopTape(BlockPos pPos) {
        SoundInstance soundInstance = this.playingTapes.get(pPos);
        if (soundInstance != null) {
            minecraft.getSoundManager().stop(soundInstance);
            this.playingTapes.remove(pPos);
        }

    }

}
