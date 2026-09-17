package net.tntmaster.tntmod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tntmaster.tntmod.Tntmod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Tntmod.MODID);

    public static final RegistryObject<SoundEvent> DISCO_GIRL = registerSoundEvents("disco_girl");

    public static final RegistryObject<SoundEvent> LEMONS = registerSoundEvents("lemons");

    public static final RegistryObject<SoundEvent> BRAIN_DAMAGE = registerSoundEvents("brain_damage");

    public static final RegistryObject<SoundEvent> SPEAR_OF_JUSTICE = registerSoundEvents("spear_of_justice");

    public static final RegistryObject<SoundEvent> JARONA = registerSoundEvents("jarona");

    public static final RegistryObject<SoundEvent> FLOWERY_GOODBYE = registerSoundEvents("flowery_goodbye");

    public static final RegistryObject<SoundEvent> JA_ORANGE = registerSoundEvents("ja_orange");

    public static final RegistryObject<SoundEvent> TAPE_EJECT = registerSoundEvents("tape_eject");

    public static final RegistryObject<SoundEvent> TAPE_INSERT = registerSoundEvents("tape_insert");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tntmod.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}


