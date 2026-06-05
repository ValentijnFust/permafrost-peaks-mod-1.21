package net.mod.permafrostpeaks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;
import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //Blocks
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GREEN_CRYSTAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.YELLOW_CRYSTAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUE_CRYSTAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RED_CRYSTAL_BLOCK);

        //Testing Blocks
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TESTING_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ANTONIO_LIBERTO_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_NETHER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_END_ORE);

        blockStateModelGenerator.registerLog(ModBlocks.DRIFTWOOD_LOG).log(ModBlocks.DRIFTWOOD_LOG).wood(ModBlocks.DRIFTWOOD_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_DRIFTWOOD_LOG).log(ModBlocks.STRIPPED_DRIFTWOOD_LOG).wood(ModBlocks.STRIPPED_DRIFTWOOD_WOOD);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DRIFTWOOD_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.DRIFTWOOD_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.DRIFTWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        //Block entities
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRYSTAL_CRUSHER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //Items
        itemModelGenerator.register(ModItems.GREEN_CRYSTAL_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.YELLOW_CRYSTAL_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLUE_CRYSTAL_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.RED_CRYSTAL_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOAT_FUR, Models.GENERATED);

        //Test Items
        itemModelGenerator.register(ModItems.ANTONIO_LIBERTO, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RED_GARNET, Models.GENERATED);

        //Armor
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_BOOTS));

        //Trees
        itemModelGenerator.register(ModBlocks.DRIFTWOOD_SAPLING.asItem(), Models.GENERATED);

        //Mobs
        itemModelGenerator.register(ModItems.MANTIS_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));


    }
}