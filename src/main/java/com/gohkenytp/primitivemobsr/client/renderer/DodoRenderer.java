
package com.gohkenytp.primitivemobsr.client.renderer;

import com.gohkenytp.primitivemobsr.client.ModModelLayers;
import com.gohkenytp.primitivemobsr.client.model.ModelDodo;
import com.gohkenytp.primitivemobsr.entities.DodoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DodoRenderer extends MobRenderer<DodoEntity, ModelDodo<DodoEntity>> {

	public DodoRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new ModelDodo<>(p_173952_.bakeLayer(ModModelLayers.DODO)), 0.75F);
	}

	@Override
	public ResourceLocation getTextureLocation(DodoEntity entity) {
		return new ResourceLocation("primitivemobsr:textures/entity/rareanimals/dodo.png");
	}
}