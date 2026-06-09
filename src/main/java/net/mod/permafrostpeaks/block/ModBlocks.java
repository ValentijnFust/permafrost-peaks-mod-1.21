package net.mod.permafrostpeaks.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.mod.permafrostpeaks.block.custom.*;
import net.mod.permafrostpeaks.world.tree.ModSaplingGenerators;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.block.custom.CrystalCrusherBlock;
import net.mod.permafrostpeaks.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.util.math.intprovider.UniformIntProvider;


public class ModBlocks {

    //To see the registered block in the inventory go to the ModItemGroups.java file and add the corresponding code there
    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .mapColor(MapColor.DARK_RED)
            ));

    public static final Block PINK_GARNET_END_ORE = registerBlock("pink_garnet_end_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 8),
                    AbstractBlock.Settings.create().strength(7f).requiresTool()));
    public static final Block PINK_GARNET_NETHER_ORE = registerBlock("pink_garnet_nether_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(1, 5),
                    AbstractBlock.Settings.create().strength(3f).requiresTool()));

    public static final Block ANTONIO_LIBERTO_BLOCK = registerBlock("antonio_liberto_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(3F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.SLIME)
                    .slipperiness(8F)
                    .mapColor(MapColor.DARK_DULL_PINK)
                    .luminance(state -> 15)
            ));

    public static final Block GREEN_CRYSTAL_BLOCK = registerBlock("green_crystal_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(2.1F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .luminance(state -> 11)
                    .mapColor(MapColor.PALE_GREEN)
            ));

    public static final Block BLUE_CRYSTAL_BLOCK = registerBlock("blue_crystal_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(2.8F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .luminance(state -> 7)
                    .mapColor(MapColor.BLUE)
            ));
    public static final Block RED_CRYSTAL_BLOCK = registerBlock("red_crystal_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(7F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .luminance(state -> 7)
                    .mapColor(MapColor.DARK_RED)
            ));

    public static final Block YELLOW_CRYSTAL_BLOCK = registerBlock("yellow_crystal_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(4F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .luminance(state -> 7)
                    .mapColor(MapColor.PALE_YELLOW)
            ));

    //Trees
    public static final Block DRIFTWOOD_LOG = registerBlock("driftwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block DRIFTWOOD_WOOD = registerBlock("driftwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_DRIFTWOOD_LOG = registerBlock("stripped_driftwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_DRIFTWOOD_WOOD = registerBlock("stripped_driftwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block DRIFTWOOD_PLANKS = registerBlock("driftwood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block DRIFTWOOD_LEAVES = registerBlock("driftwood_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block DRIFTWOOD_SAPLING = registerBlock("driftwood_sapling",
            new ModSaplingBlock(ModSaplingGenerators.DRIFTWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), Blocks.STONE));

    //If there's anything that needs testing use the block below for that
    //There are also settings for this block in the PermaFrostPeaksClient.java file
    public static final Block TESTING_BLOCK = registerBlock("testing_block",
            new TransparentBlock(AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(0.3F)
                    .sounds(BlockSoundGroup.ANCIENT_DEBRIS)
                    .nonOpaque()
            ));

    public static final Block CRYSTAL_CRUSHER = registerBlock("crystal_crusher",
            new CrystalCrusherBlock(AbstractBlock.Settings.create()));

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