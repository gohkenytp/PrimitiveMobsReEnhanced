package com.gohkenytp.primitivemobsr;

import com.gohkenytp.primitivemobsr.client.ClientRegister;
import com.gohkenytp.primitivemobsr.init.PMRBiomeModifiers;
import com.gohkenytp.primitivemobsr.init.PMREntityTypes;
import com.gohkenytp.primitivemobsr.init.PMRItems;
import com.gohkenytp.primitivemobsr.init.PMRSoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file

@Mod(PrimitiveMobsR.MOD_ID)
public class PrimitiveMobsR {
    public static final String MOD_ID = "primitivemobsr";
    public static final CreativeModeTab TAB = new CreativeModeTab("pmr_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PMRItems.DODO_SPAWN_EGG.get());
        }

    };

    public PrimitiveMobsR() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        PMREntityTypes.ENTITY_TYPES.register(modBus);
        PMRItems.ITEMS.register(modBus);
        PMRSoundEvents.SOUND_EVENTS.register(modBus);
        PMRBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modBus);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegister::setup));
    }

    private void setup(final FMLCommonSetupEvent event) {
        PMREntityTypes.spawnPlacementSetup();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {
    }
}