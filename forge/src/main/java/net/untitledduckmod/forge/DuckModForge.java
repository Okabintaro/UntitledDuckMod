package net.untitledduckmod.forge;

import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.untitledduckmod.DuckMod;
import net.untitledduckmod.ModEntityTypes;
import net.untitledduckmod.ModItems;
import net.untitledduckmod.ModSoundEvents;

@Mod(DuckMod.MOD_ID)
public class DuckModForge {
    public DuckModForge() {
        DuckMod.init();
        ModItems.register(null);
        ModEntityTypes.register(null);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModSetup {
        @SubscribeEvent
        public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
            ModSoundEvents.register(event);
        }

        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event) {
            ModEntityTypes.registerAttributes();
            ModEntityTypesImpl.setupSpawning();
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
            ForgeSpawnEggItem.addModdedEggs();
        }

        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            DuckModForgeClientSetup.setupEntityRenderers();
        }
    }
}
