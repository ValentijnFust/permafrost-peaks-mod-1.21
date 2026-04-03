package net.mod.permafrostpeaks.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;

public class ModBlocks {
// HOW TO USE
// Copy the function below and fill in the spaces between brackets []
// public static final Block [BLOCK_NAME_CAPITALIZED] = registerBlock("[block_name_lowercased]",
//            new Block(AbstractBlock.Settings.create() [.settings(of block)] ));
// see the file: [block of net.minecraft.block] for more information


    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            new Block(AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK )));
    public static final Block ANTONIO_LIBERTO_BLOCK = registerBlock("antonio_liberto_block",
            new Block(AbstractBlock.Settings.create().strength(3f).requiresTool().sounds(BlockSoundGroup.HONEY )));
    public static final Block GROEN_KRISTAL_BLOCK = registerBlock("groen_kristal_block",
            new Block(AbstractBlock.Settings.create().strength(3f).requiresTool().sounds(BlockSoundGroup.HONEY )));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PermaFrostPeaks.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PermaFrostPeaks.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PermaFrostPeaks.LOGGER.info("Registering Mod Blocks for " + PermaFrostPeaks.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.PINK_GARNET_BLOCK);
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.ANTONIO_LIBERTO_BLOCK);
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.GROEN_KRISTAL_BLOCK);
        });
    }
}
