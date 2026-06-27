package net.mod.permafrostpeaks.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.minecraft.item.*;
import net.mod.permafrostpeaks.entity.ModEntities;

public class ModItems {

//Item Registering
//To see the registered item in the inventory go to the ModItemGroups.java file and add the corresponding code there
    public static final Item GOAT_FUR = registerItem("goat_fur", new Item(new Item.Settings()));

    public static final Item BLUE_CRYSTAL_SHARD = registerItem("blue_crystal_shard", new Item(new Item.Settings()));
    public static final Item RED_CRYSTAL_SHARD = registerItem("red_crystal_shard", new Item(new Item.Settings()));
    public static final Item YELLOW_CRYSTAL_SHARD = registerItem("yellow_crystal_shard", new Item(new Item.Settings()));
    public static final Item GREEN_CRYSTAL_SHARD = registerItem("green_crystal_shard", new Item(new Item.Settings()));

    public static final Item BLUE_CRYSTAL_DUST = registerItem("blue_crystal_dust", new Item(new Item.Settings()
            .fireproof()
            .maxCount(16)));
    public static final Item RED_CRYSTAL_DUST = registerItem("red_crystal_dust", new Item(new Item.Settings()
            .fireproof()
            .maxCount(16)));
    public static final Item YELLOW_CRYSTAL_DUST = registerItem("yellow_crystal_dust", new Item(new Item.Settings()
            .fireproof()
            .maxCount(16)));
    public static final Item GREEN_CRYSTAL_DUST = registerItem("green_crystal_dust", new Item(new Item.Settings()
            .fireproof()
            .maxCount(16)));

//Armor registering
    public static final Item FUR_HELMET = registerItem("fur_helmet",
            new ArmorItem(ModArmorMaterials.FUR_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));

    public static final Item FUR_CHESTPLATE = registerItem("fur_chestplate",
            new ArmorItem(ModArmorMaterials.FUR_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15))));

    public static final Item FUR_LEGGINGS = registerItem("fur_leggings",
            new ArmorItem(ModArmorMaterials.FUR_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));

    public static final Item FUR_BOOTS = registerItem("fur_boots",
            new ArmorItem(ModArmorMaterials.FUR_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));

    public static final Item TROLL_SPAWN_EGG = registerItem("troll_spawn_egg",
            new SpawnEggItem(ModEntities.TROLL, 0x9dc783, 0xbfaf5f, new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PermaFrostPeaks.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PermaFrostPeaks.LOGGER.info("Registering Mod Items for " + PermaFrostPeaks.MOD_ID);
    }
}