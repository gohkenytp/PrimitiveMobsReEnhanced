package com.gohkenytp.primitivemobsr.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelGroveSprite<T extends Entity> extends EntityModel<T> {
       public final ModelPart bipedHead;
        public final ModelPart bipedHeadwear;
        public final ModelPart bipedBody;
        public final ModelPart bipedRightArm;
        public final ModelPart bipedLeftArm;
        public final ModelPart bipedRightLeg;
        public final ModelPart bipedLeftLeg;
        public final ModelPart stem;
        public final ModelPart leaf;

        public ModelGroveSprite(ModelPart root) {
            this.bipedHead = root.getChild("bipedHead");
            this.bipedHeadwear = root.getChild("bipedHeadwear");
            this.bipedBody = root.getChild("bipedBody");
            this.bipedRightArm = root.getChild("bipedRightArm");
            this.bipedLeftArm = root.getChild("bipedLeftArm");
            this.bipedRightLeg = root.getChild("bipedRightLeg");
            this.bipedLeftLeg = root.getChild("bipedLeftLeg");
            this.stem = root.getChild("stem");
            this.leaf = root.getChild("leaf");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();
            PartDefinition bipedHead = partdefinition.addOrReplaceChild("bipedHead",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 9.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
                    PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition bipedHeadwear = partdefinition.addOrReplaceChild("bipedHeadwear",
                    CubeListBuilder.create().texOffs(23, 0).addBox(-3.0F, 9.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
                    PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition bipedBody = partdefinition.addOrReplaceChild("bipedBody",
                    CubeListBuilder.create().texOffs(24, 12).addBox(-3.0F, 13.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
                    PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition bipedRightArm = partdefinition.addOrReplaceChild("bipedRightArm",
                    CubeListBuilder.create().texOffs(12, 12).addBox(3.0F, 15.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
                    PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition bipedLeftArm = partdefinition.addOrReplaceChild("bipedLeftArm",
                    CubeListBuilder.create().texOffs(12, 12).addBox(-6.0F, 15.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
                    PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition bipedRightLeg = partdefinition.addOrReplaceChild("bipedRightLeg",
                    CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 19.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
                    PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition bipedLeftLeg = partdefinition.addOrReplaceChild("bipedLeftLeg",
                    CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, 19.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
                    PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition stem = partdefinition.addOrReplaceChild("stem",
                    CubeListBuilder.create().texOffs(24, 8).addBox(-1.0F, 10.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
                    PartPose.offset(0.0F, -4.0F, 0.0F));
            PartDefinition leaf = partdefinition.addOrReplaceChild("leaf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition cube_r1 = leaf.addOrReplaceChild("cube_r1",
                    CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, 4.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
            return LayerDefinition.create(meshdefinition, 64, 32);
        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green,
                                   float blue, float alpha) {
            bipedHead.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            bipedHeadwear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            bipedBody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            bipedRightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            bipedLeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            bipedRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            bipedLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            stem.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leaf.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        bipedHead.yRot = netHeadYaw / 57.29578F;
        bipedHead.xRot = headPitch / 57.29578F;

        stem.yRot = bipedHeadwear.yRot = bipedHead.yRot;
        stem.xRot = bipedHeadwear.xRot = bipedHead.xRot;

        bipedLeftArm.xRot  = Mth.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.8F * limbSwingAmount;
        bipedRightArm.xRot = -Mth.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.8F * limbSwingAmount;
        bipedRightLeg.xRot = Mth.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.8F * limbSwingAmount;
        bipedLeftLeg.xRot = -Mth.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.8F * limbSwingAmount;

        leaf.yRot = netHeadYaw / 57.29578F;;
        leaf.xRot = headPitch / 57.29578F * 0.5f;
    }

}