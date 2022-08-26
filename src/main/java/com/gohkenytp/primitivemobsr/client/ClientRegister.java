package com.gohkenytp.primitivemobsr.client;

import com.gohkenytp.primitivemobsr.PrimitiveMobsR;
import com.gohkenytp.primitivemobsr.client.model.ModelDodo;
import com.gohkenytp.primitivemobsr.client.model.ModelGroveSprite;
import com.gohkenytp.primitivemobsr.client.renderer.DodoRenderer;
import com.gohkenytp.primitivemobsr.client.renderer.GroveSpriteRenderer;
import com.gohkenytp.primitivemobsr.client.renderer.SkeletonWarriorRenderer;
import com.gohkenytp.primitivemobsr.init.PMREntityTypes;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = PrimitiveMobsR.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegister {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PMREntityTypes.DODO.get(), DodoRenderer::new);
        event.registerEntityRenderer(PMREntityTypes.DODO_EGG.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(PMREntityTypes.SKELETON_WARRIOR.get(), SkeletonWarriorRenderer::new);
        event.registerEntityRenderer(PMREntityTypes.GROVE_SPRITE.get(), GroveSpriteRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.DODO, ModelDodo::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GROVE_SPRITE, ModelGroveSprite::createBodyLayer);
    }

    public static void setup(FMLCommonSetupEvent event) {
    }
}
