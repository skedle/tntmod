package net.tntmaster.tntmod.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tntmaster.tntmod.Tntmod;
import net.tntmaster.tntmod.entity.ModEntities;
import net.tntmaster.tntmod.entity.custom.PupfishEntity;

@Mod.EventBusSubscriber(modid = Tntmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModEventBusEvent {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PUPFISH.get(), PupfishEntity.createAttributes().build());
    }
}

