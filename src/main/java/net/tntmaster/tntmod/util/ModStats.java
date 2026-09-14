package net.tntmaster.tntmod.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tntmaster.tntmod.Tntmod;

public class ModStats {

    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS =
            DeferredRegister.create(Registries.CUSTOM_STAT, Tntmod.MODID);

    public static final RegistryObject<ResourceLocation> PLAY_TAPE =
            CUSTOM_STATS.register("play_tape", () ->
                    ResourceLocation.fromNamespaceAndPath(Tntmod.MODID, "play_tape"));

    public static void register(IEventBus eventBus) {
        CUSTOM_STATS.register(eventBus);
    }


}



