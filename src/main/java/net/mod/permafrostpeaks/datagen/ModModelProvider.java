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

    //Block Model generator
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

        registerCrystalCrusher(blockStateModelGenerator);
    }

    //6-sided Block Model generator
    private void registerCrystalCrusher(BlockStateModelGenerator generator) {
        TextureMap textures = new TextureMap()
                .put(TextureKey.DOWN, Identifier.of("permafrostpeaks", "block/crystal_crusher_bottom"))
                .put(TextureKey.UP, Identifier.of("permafrostpeaks", "block/crystal_crusher_top"))
                .put(TextureKey.NORTH, Identifier.of("permafrostpeaks", "block/crystal_crusher_front"))
                .put(TextureKey.SOUTH, Identifier.of("permafrostpeaks", "block/crystal_crusher_back"))
                .put(TextureKey.WEST, Identifier.of("permafrostpeaks", "block/crystal_crusher_left"))
                .put(TextureKey.EAST, Identifier.of("permafrostpeaks", "block/crystal_crusher_right"))
                .put(TextureKey.PARTICLE, Identifier.of("permafrostpeaks", "block/crystal_crusher_top"));

        Identifier model = Models.CUBE.upload(
                ModBlocks.CRYSTAL_CRUSHER,
                textures,
                generator.modelCollector
        );

        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(
                        ModBlocks.CRYSTAL_CRUSHER,
                        BlockStateVariant.create()
                                .put(VariantSettings.MODEL, model)
                )
        );
    }

    //Item Model generator
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
        itemModelGenerator.register(ModItems.TROLL_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));


    }
}