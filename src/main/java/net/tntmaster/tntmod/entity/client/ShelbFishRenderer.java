package net.tntmaster.tntmod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tntmaster.tntmod.Tntmod;
import net.tntmaster.tntmod.entity.custom.PupfishEntity;

public class ShelbFishRenderer extends MobRenderer<PupfishEntity, PupfishModel<PupfishEntity>> {

    public ShelbFishRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PupfishModel<>(pContext.bakeLayer(ModModelLayers.PUPFISH_LAYER)), .3f);
    }

    @Override
    public ResourceLocation getTextureLocation(PupfishEntity pEntity) {
        return new ResourceLocation(Tntmod.MODID, "textures/entity/shelbfish.png");
    }
}
