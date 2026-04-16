package net.mod.permafrostpeaks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

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
    }
}