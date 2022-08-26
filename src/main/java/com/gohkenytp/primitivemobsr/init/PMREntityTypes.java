package com.gohkenytp.primitivemobsr.init;

import com.gohkenytp.primitivemobsr.PrimitiveMobsR;
import com.gohkenytp.primitivemobsr.entities.DodoEggEntity;
import com.gohkenytp.primitivemobsr.entities.DodoEntity;
import com.gohkenytp.primitivemobsr.entities.GroveSpriteEntity;
import com.gohkenytp.primitivemobsr.entities.SkeletonWarriorEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = PrimitiveMobsR.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PMREntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PrimitiveMobsR.MOD_ID);
    public static final RegistryObject<EntityType<DodoEntity>> DODO = ENTITY_TYPES.register("dodo", () -> EntityType.Builder.of(DodoEntity::new, MobCategory.CREATURE).sized(0.75F, 0.75F).clientTrackingRange(10).build(prefix("dodo")));
    public static final RegistryObject<EntityType<SkeletonWarriorEntity>> SKELETON_WARRIOR = ENTITY_TYPES.register("skeleton_warrior", () -> EntityType.Builder.of(SkeletonWarriorEntity::new, MobCategory.MONSTER).clientTrackingRange(10).build(prefix("skeleton_warrior")));
    public static final RegistryObject<EntityType<GroveSpriteEntity>> GROVE_SPRITE = ENTITY_TYPES.register("grove_sprite", () -> EntityType.Builder.of(GroveSpriteEntity::new, MobCategory.CREATURE).sized(0.5F, 1.0F).clientTrackingRange(10).build(prefix("grove_sprite")));
    public static final RegistryObject<EntityType<DodoEggEntity>> DODO_EGG = ENTITY_TYPES.register("dodo_egg", () -> EntityType.Builder.<DodoEggEntity>of(DodoEggEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("dodo_egg")));

    private static String prefix(String path) {
        return PrimitiveMobsR.MOD_ID + "." + path;
    }

    public static void spawnPlacementSetup() {
        SpawnPlacements.register(DODO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DodoEntity::checkDodoSpawnRules);
        SpawnPlacements.register(SKELETON_WARRIOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonWarriorEntity::checkSkeletonWarriorSpawnRules);
        SpawnPlacements.register(GROVE_SPRITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GroveSpriteEntity::checkGroveSpriteSpawnRules);
    }

    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
        event.put(DODO.get(), DodoEntity.createAttributes().build());
        event.put(SKELETON_WARRIOR.get(), SkeletonWarriorEntity.createAttributes().build());
        event.put(GROVE_SPRITE.get(), GroveSpriteEntity.createAttributes().build());
    }
}
