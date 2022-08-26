package com.gohkenytp.primitivemobsr.world.biome.modifiers;

import com.gohkenytp.primitivemobsr.init.PMRBiomeModifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record PrimitiveBiomeModifier(HolderSet<Biome> biomes, HolderSet<Biome> blacklist_biomes,
                               MobSpawnSettings.SpawnerData spawn,
                               MobCategory category) implements BiomeModifier {
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && this.biomes.contains(biome) && !blacklist_biomes.contains(biome)) {
            builder.getMobSpawnSettings().addSpawn(category, spawn);

        }
    }

    public static Codec<PrimitiveBiomeModifier> makeCodec() {
        return RecordCodecBuilder.create(builder -> builder
                .group(Biome.LIST_CODEC.fieldOf("biomes").forGetter(PrimitiveBiomeModifier::biomes),
                        Biome.LIST_CODEC.fieldOf("blacklist_biomes").forGetter(PrimitiveBiomeModifier::blacklist_biomes),
                        MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(PrimitiveBiomeModifier::spawn),
                        MobCategory.CODEC.fieldOf("mob_category").forGetter(PrimitiveBiomeModifier::category))
                .apply(builder, PrimitiveBiomeModifier::new));
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return PMRBiomeModifiers.PRIMITIVE_ENTITY_MODIFIER_TYPE.get();
    }
}