package net.mod.permafrostpeaks.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;

public class ModBlocks {

    private static Block RegisterBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PermaFrostPeaks.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PermaFrostPeaks.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }


    public static void registerModBlocks() {
        PermaFrostPeaks.LOGGER.info("Registering Mod Blocks for " + PermaFrostPeaks.MOD_ID);
    }
}
