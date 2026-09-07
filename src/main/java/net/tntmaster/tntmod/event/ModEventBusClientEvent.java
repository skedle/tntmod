package net.tntmaster.tntmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tntmaster.tntmod.Tntmod;
import net.tntmaster.tntmod.entity.client.ModModelLayers;
import net.tntmaster.tntmod.entity.client.PupfishModel;

@Mod.EventBusSubscriber(modid = Tntmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvent {

    @SubscribeEvent
    public static void  registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.PUPFISH_LAYER, PupfishModel::createBodyLayer);
    }
}
