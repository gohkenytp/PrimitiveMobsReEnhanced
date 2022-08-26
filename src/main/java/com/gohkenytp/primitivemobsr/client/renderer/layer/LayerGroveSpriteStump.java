package com.gohkenytp.primitivemobsr.client.renderer.layer;

import com.gohkenytp.primitivemobsr.client.model.ModelGroveSprite;
import com.gohkenytp.primitivemobsr.entities.GroveSpriteEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class LayerGroveSpriteStump extends RenderLayer<GroveSpriteEntity, ModelGroveSprite<GroveSpriteEntity>> {

    public LayerGroveSpriteStump(RenderLayerParent<GroveSpriteEntity, ModelGroveSprite<GroveSpriteEntity>> p_117346_) {
        super(p_117346_);
    }

    @Override
    public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, GroveSpriteEntity p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        new ResourceLocation("primitivemobsr:textures/entity/grovesprite/grovestump.png");
    }
}
