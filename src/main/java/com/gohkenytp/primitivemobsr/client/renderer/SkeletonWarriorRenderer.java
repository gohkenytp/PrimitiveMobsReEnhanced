
package com.gohkenytp.primitivemobsr.client.renderer;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class SkeletonWarriorRenderer extends HumanoidMobRenderer<AbstractSkeleton, SkeletonModel<AbstractSkeleton>> {

	private static final ResourceLocation SKELETON_LOCATION = new ResourceLocation("primitivemobsr:textures/entity/skeletonwarrior/skeletonwarrior.png");

	public SkeletonWarriorRenderer(EntityRendererProvider.Context p_174380_) {
		this(p_174380_, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
	}

	public SkeletonWarriorRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
		super(p_174382_, new SkeletonModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonModel<>(p_174382_.bakeLayer(p_174384_)), new SkeletonModel<>(p_174382_.bakeLayer(p_174385_))));
	}

	public ResourceLocation getTextureLocation(AbstractSkeleton p_115941_) {
		return SKELETON_LOCATION;
	}

}