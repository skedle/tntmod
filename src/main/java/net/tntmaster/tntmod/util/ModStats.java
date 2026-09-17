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

    public static final RegistryObject<ResourceLocation> JARONA =
            registerStat("jarona");

    private static RegistryObject<ResourceLocation> registerStat(String name) {
        return CUSTOM_STATS.register(name, () -> ResourceLocation.fromNamespaceAndPath(Tntmod.MODID, "jarona"));
    }

    public static void register(IEventBus eventBus) {
        CUSTOM_STATS.register(eventBus);
    }


}



