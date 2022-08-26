package com.gohkenytp.primitivemobsr.init;


import com.gohkenytp.primitivemobsr.PrimitiveMobsR;
import com.gohkenytp.primitivemobsr.world.biome.modifiers.PrimitiveBiomeModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PMRBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, PrimitiveMobsR.MOD_ID);

    public static final RegistryObject<Codec<PrimitiveBiomeModifier>> PRIMITIVE_ENTITY_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("primitive_entity_modifier", PrimitiveBiomeModifier::makeCodec);

}