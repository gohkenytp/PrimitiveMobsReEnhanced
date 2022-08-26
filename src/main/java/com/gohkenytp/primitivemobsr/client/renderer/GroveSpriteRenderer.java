
package com.gohkenytp.primitivemobsr.client.renderer;

import com.gohkenytp.primitivemobsr.client.ModModelLayers;
import com.gohkenytp.primitivemobsr.client.model.ModelGroveSprite;
import com.gohkenytp.primitivemobsr.client.renderer.layer.LayerGroveSpriteStump;
import com.gohkenytp.primitivemobsr.entities.GroveSpriteEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GroveSpriteRenderer extends MobRenderer<GroveSpriteEntity, ModelGroveSprite<GroveSpriteEntity>> {
    public GroveSpriteRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelGroveSprite<>(context.bakeLayer(ModModelLayers.GROVE_SPRITE)), 0.3F);
        this.addLayer(new LayerGroveSpriteStump(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GroveSpriteEntity entity) {
        if (entity.isCinderSprite())
            return new ResourceLocation("primitivemobsr:textures/entity/grovesprite/grovecinder.png");

        return new ResourceLocation("primitivemobsr:textures/entity/grovesprite/grovebase.png");

    }
}
