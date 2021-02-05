package net.untitledduckmod.fabric;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;
import net.untitledduckmod.items.DuckEggItem;
import net.untitledduckmod.registration.ItemSuppliers;

public class ModItemsImpl {
    private static SpawnEggItem DUCK_SPAWN_EGG;
    private static DuckEggItem DUCK_EGG;
    public static void register(Object optionalEvent) {
        DUCK_SPAWN_EGG = Registry.register(Registry.ITEM, ItemSuppliers.DUCK_SPAWN_EGG_ID, ItemSuppliers.DUCK_SPAWN_EGG.get());
        DUCK_EGG = Registry.register(Registry.ITEM, ItemSuppliers.DUCK_EGG_ID, ItemSuppliers.DUCK_EGG.get());
    }

    public static void setup(Object optionalEvent) {
    }

    public static Item getDuckSpawnEgg() {
        return DUCK_SPAWN_EGG;
    }

    public static Item getDuckEgg() {
        return DUCK_EGG;
    }
}