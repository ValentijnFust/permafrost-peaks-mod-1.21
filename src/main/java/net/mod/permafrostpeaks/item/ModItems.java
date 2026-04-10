package net.mod.permafrostpeaks.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;

public class ModItems {

    //To see the registered item in the inventory go to the ModItemGroups.java file and add the corresponding code there
    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));

    public static final Item RED_GARNET = registerItem("red_garnet", new Item(new Item.Settings()));

    public static final Item ANTONIO_LIBERTO = registerItem("antonio_liberto", new Item(new Item.Settings()));

    public static final Item GOAT_FUR = registerItem("goat_fur", new Item(new Item.Settings()));

    public static final Item BLUE_CRYSTAL_SHARD = registerItem("blue_crystal_shard", new Item(new Item.Settings()));

    public static final Item RED_CRYSTAL_SHARD = registerItem("red_crystal_shard", new Item(new Item.Settings()));

    public static final Item YELLOW_CRYSTAL_SHARD = registerItem("yellow_crystal_shard", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PermaFrostPeaks.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PermaFrostPeaks.LOGGER.info("Registering Mod Items for " + PermaFrostPeaks.MOD_ID);
    }
}