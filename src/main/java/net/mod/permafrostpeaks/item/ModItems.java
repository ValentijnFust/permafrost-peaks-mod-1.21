package net.mod.permafrostpeaks.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;

public class ModItems {
    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RED_GARNET = registerItem("red_garnet", new Item(new Item.Settings()));
    public static final Item ANTONIO_LIBERTO = registerItem("antonio_liberto", new Item(new Item.Settings()));
    public static final Item GOAT_FUR = registerItem("goat_fur", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PermaFrostPeaks.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PermaFrostPeaks.LOGGER.info("Registering Mod Items for " + PermaFrostPeaks.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(PINK_GARNET);
        });
        PermaFrostPeaks.LOGGER.info("Registering Mod Items for " + PermaFrostPeaks.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(RED_GARNET);
        });
        PermaFrostPeaks.LOGGER.info("Registering Mod Items for " + PermaFrostPeaks.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(GOAT_FUR);
        });
        PermaFrostPeaks.LOGGER.info("Registering Mod Items for " + PermaFrostPeaks.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ANTONIO_LIBERTO);
        });
    }
}