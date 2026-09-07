package net.tntmaster.tntmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.tntmaster.tntmod.entity.animations.ModAnimationDefinitions;
import net.tntmaster.tntmod.entity.custom.PupfishEntity;

import static net.minecraftforge.client.model.lighting.ForgeModelBlockRenderer.render;

public class PupfishModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart fish;
	private final ModelPart back_fin;
	private final ModelPart left_fin;
	private final ModelPart right_fin;

	public PupfishModel(ModelPart root) {
		this.fish = root.getChild("fish");
		this.back_fin = root.getChild("back fin");
		this.left_fin = root.getChild("left fin");
		this.right_fin = root.getChild("right fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition fish = partdefinition.addOrReplaceChild("fish", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -4.0F, -5.0F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(3.5F, -6.0F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(14, 13).addBox(3.5F, 0.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 24.0F, 0.0F));

		PartDefinition back_fin = partdefinition.addOrReplaceChild("back fin", CubeListBuilder.create().texOffs(8, 13).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 22.0F, 4.0F));

		PartDefinition left_fin = partdefinition.addOrReplaceChild("left fin", CubeListBuilder.create(), PartPose.offset(1.0F, 23.0F, -1.0F));

		PartDefinition fin_r1 = left_fin.addOrReplaceChild("fin_r1", CubeListBuilder.create().texOffs(18, 17).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition right_fin = partdefinition.addOrReplaceChild("right fin", CubeListBuilder.create(), PartPose.offset(-2.0F, 23.0F, -1.0F));

		PartDefinition fin_r2 = right_fin.addOrReplaceChild("fin_r2", CubeListBuilder.create().texOffs(14, 17).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T pEntity, float limbSwing, float limbSwingAmount, float pAgeInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		float f = 1.0F;
		if (!pEntity.isInWater()) {
			f = 1.5F;
		}

		this.back_fin.yRot = -f * 0.45F * Mth.sin(0.6F * pAgeInTicks);
		this.right_fin.yRot = -f * 0.25F * Mth.sin(0.75F * pAgeInTicks);
		this.left_fin.yRot = -f * 0.25F * Mth.sin(0.75F * pAgeInTicks);



	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		fish.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		back_fin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_fin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_fin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return fish;
	}
}