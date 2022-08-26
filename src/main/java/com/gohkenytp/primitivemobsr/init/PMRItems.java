package com.gohkenytp.primitivemobsr.init;

import com.gohkenytp.primitivemobsr.PrimitiveMobsR;
import com.gohkenytp.primitivemobsr.items.DodoEggItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PMRItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PrimitiveMobsR.MOD_ID);

    // Food
    public static final RegistryObject<Item> DODO_MEAT = ITEMS.register("dodo_meat", () -> new Item(new Item.Properties().tab(PrimitiveMobsR.TAB).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).meat().build())));
    public static final RegistryObject<Item> COOKED_DODO_MEAT = ITEMS.register("cooked_dodo_meat", () -> new Item(new Item.Properties().tab(PrimitiveMobsR.TAB).food(new FoodProperties.Builder().nutrition(8).saturationMod(0.85F).meat().build())));

    // Misc. Items
    public static final RegistryObject<Item> DODO_EGG = ITEMS.register("dodo_egg", () -> new DodoEggItem((new Item.Properties().tab(PrimitiveMobsR.TAB))));
    // Spawn Eggs
    public static RegistryObject<ForgeSpawnEggItem> DODO_SPAWN_EGG = ITEMS.register("dodo_spawn_egg", () -> new ForgeSpawnEggItem(PMREntityTypes.DODO, 0x725643, 0xBCA18C, new Item.Properties().tab(PrimitiveMobsR.TAB)));
    public static RegistryObject<ForgeSpawnEggItem> SKELETON_WARRIOR_SPAWN_EGG = ITEMS.register("skeleton_warrior_spawn_egg", () -> new ForgeSpawnEggItem(PMREntityTypes.SKELETON_WARRIOR, 0xABA188, 0x6C5239, new Item.Properties().tab(PrimitiveMobsR.TAB)));
    public static RegistryObject<ForgeSpawnEggItem> GROVE_SPRITE_SPAWN_EGG = ITEMS.register("grove_sprite_spawn_egg", () -> new ForgeSpawnEggItem(PMREntityTypes.GROVE_SPRITE, 0x5B4E3D, 0x62A72F, new Item.Properties().tab(PrimitiveMobsR.TAB)));

}
