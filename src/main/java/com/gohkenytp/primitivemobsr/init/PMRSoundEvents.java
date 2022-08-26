package com.gohkenytp.primitivemobsr.init;

import com.gohkenytp.primitivemobsr.PrimitiveMobsR;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PMRSoundEvents {
    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PrimitiveMobsR.MOD_ID);


    public static final RegistryObject<SoundEvent> GROVESPRITE_ANGRY = SOUND_EVENTS.register("entity.grovesprite.angry", () -> new SoundEvent(new ResourceLocation(PrimitiveMobsR.MOD_ID, "entity.grovesprite.angry")));

    public static final RegistryObject<SoundEvent> GROVESPRITE_HURT = SOUND_EVENTS.register("entity.grovesprite.hurt", () -> new SoundEvent(new ResourceLocation(PrimitiveMobsR.MOD_ID, "entity.grovesprite.hurt")));

    public static final RegistryObject<SoundEvent> GROVESPRITE_IDLE = SOUND_EVENTS.register("entity.grovesprite.idle", () -> new SoundEvent(new ResourceLocation(PrimitiveMobsR.MOD_ID, "entity.grovesprite.idle")));

    public static final RegistryObject<SoundEvent> GROVESPRITE_FRIEND = SOUND_EVENTS.register("entity.grovesprite.friend", () -> new SoundEvent(new ResourceLocation(PrimitiveMobsR.MOD_ID, "entity.grovesprite.friend")));

    public static final RegistryObject<SoundEvent> GROVESPRITE_THANKS = SOUND_EVENTS.register("entity.grovesprite.thanks", () -> new SoundEvent(new ResourceLocation(PrimitiveMobsR.MOD_ID, "entity.grovesprite.thanks")));

    public static final RegistryObject<SoundEvent> GROVESPRITE_DIE = SOUND_EVENTS.register("entity.grovesprite.death", () -> new SoundEvent(new ResourceLocation(PrimitiveMobsR.MOD_ID, "entity.grovesprite.death")));

}
