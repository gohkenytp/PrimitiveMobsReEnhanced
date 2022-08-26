package com.gohkenytp.primitivemobsr.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelDodo<T extends Entity> extends EntityModel<T> {
	private final ModelPart Body;
	private final ModelPart Neck;
	private final ModelPart Beak;
	private final ModelPart Wing1;
	private final ModelPart Wing2;
	private final ModelPart Leg1;
	private final ModelPart Leg2;
	private final ModelPart Feet1;
	private final ModelPart Feet2;
	private final ModelPart Tail;

	public ModelDodo(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Neck = root.getChild("Neck");
		this.Beak = root.getChild("Beak");
		this.Wing1 = root.getChild("Wing1");
		this.Wing2 = root.getChild("Wing2");
		this.Leg1 = root.getChild("Leg1");
		this.Leg2 = root.getChild("Leg2");
		this.Feet1 = root.getChild("Feet1");
		this.Feet2 = root.getChild("Feet2");
		this.Tail = root.getChild("Tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 12).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, -0.192F, 0.0F, 0.0F));

		PartDefinition Neck = partdefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(29, 0).mirror().addBox(-2.0F, -10.0F, -3.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 16.0F, -6.0F, -0.2443F, 0.0F, 0.0F));

		PartDefinition Beak = partdefinition.addOrReplaceChild("Beak", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(-1.0F, -10.0F, -3.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 16.0F, -6.0F, 0.2793F, 0.0F, 0.0F));

		PartDefinition Wing1 = partdefinition.addOrReplaceChild("Wing1", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 12.0F, -3.0F, -0.192F, 0.0F, 0.0F));

		PartDefinition Wing2 = partdefinition.addOrReplaceChild("Wing2", CubeListBuilder.create().texOffs(28, 14).mirror().addBox(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 12.0F, -3.0F, -0.192F, 0.0F, 0.0F));

		PartDefinition Leg1 = partdefinition.addOrReplaceChild("Leg1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 20.0F, 0.0F));

		PartDefinition Leg2 = partdefinition.addOrReplaceChild("Leg2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 20.0F, 0.0F));

		PartDefinition Feet1 = partdefinition.addOrReplaceChild("Feet1", CubeListBuilder.create().texOffs(1, 8).mirror().addBox(-1.5F, 3.0F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 20.0F, 0.0F));

		PartDefinition Feet2 = partdefinition.addOrReplaceChild("Feet2", CubeListBuilder.create().texOffs(1, 8).mirror().addBox(-1.5F, 3.0F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 20.0F, 0.0F));

		PartDefinition Tail = partdefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(4, 0).mirror().addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 13.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Neck.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Beak.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Wing1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Wing2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Feet1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Feet2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		this.Neck.xRot = f4 / (220F / (float)Math.PI) - 0.2443461F;
		this.Neck.yRot = f3 / (220F / (float)Math.PI);
		this.Beak.xRot = f4 / (220F / (float)Math.PI) + 0.2792527F;
		this.Beak.yRot = f3 / (220F / (float)Math.PI);
		this.Leg2.xRot = Mth.cos(f* 0.6662F) * 1.4F * f1;
		this.Leg1.xRot = Mth.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.Feet1.xRot = this.Leg1.xRot;
		this.Feet2.xRot = this.Leg2.xRot;
		this.Wing2.zRot = f;
		this.Wing1.zRot = f;
	}
}
