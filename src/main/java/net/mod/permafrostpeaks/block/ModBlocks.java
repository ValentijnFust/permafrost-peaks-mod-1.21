package net.mod.permafrostpeaks.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;

public class ModBlocks {

    //To see the registered block in the inventory go to the ModItemGroups.java file and add the corresponding code there
    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));

    public static final Block ANTONIO_LIBERTO_BLOCK = registerBlock("antonio_liberto_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(3F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.SLIME)
                    .slipperiness(8F)
            ));

    public static final Block GREEN_CRYSTAL_BLOCK = registerBlock("green_crystal_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(0.3F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .luminance(state -> 15)
            ));

    public static final Block BLUE_CRYSTAL_BLOCK = registerBlock("blue_crystal_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(3F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .luminance(state -> 7)
            ));
    public static final Block RED_CRYSTAL_BLOCK = registerBlock("red_crystal_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(7F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .luminance(state -> 7)
            ));

    public static final Block YELLOW_CRYSTAL_BLOCK = registerBlock("yellow_crystal_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(7F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .luminance(state -> 7)
            ));

    //If there's anything that needs testing use the block below for that
    //There are also settings for this block in the PermaFrostPeaksClient.java file
    public static final Block TESTING_BLOCK = registerBlock("testing_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(0.3F)
                    .sounds(BlockSoundGroup.ANCIENT_DEBRIS)
                    .nonOpaque()
            ));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PermaFrostPeaks.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PermaFrostPeaks.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PermaFrostPeaks.LOGGER.info(PermaFrostPeaks.MOD_ID);
    }
}