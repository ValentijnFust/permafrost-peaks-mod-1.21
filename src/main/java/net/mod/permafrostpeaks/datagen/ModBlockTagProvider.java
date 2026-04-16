package net.mod.permafrostpeaks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                //Blocks
                .add(ModBlocks.GREEN_CRYSTAL_BLOCK)
                .add(ModBlocks.YELLOW_CRYSTAL_BLOCK)
                .add(ModBlocks.BLUE_CRYSTAL_BLOCK)
                .add(ModBlocks.RED_CRYSTAL_BLOCK)

                //Testing blocks
                .add(ModBlocks.PINK_GARNET_BLOCK)
                .add(ModBlocks.TESTING_BLOCK)
                .add(ModBlocks.ANTONIO_LIBERTO_BLOCK)

        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)

        ;

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GREEN_CRYSTAL_BLOCK)
                .add(ModBlocks.YELLOW_CRYSTAL_BLOCK)
                .add(ModBlocks.BLUE_CRYSTAL_BLOCK)
                .add(ModBlocks.RED_CRYSTAL_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)

        ;
    }
}