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
        ;

        //Needs Iron Tools to mine
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GREEN_CRYSTAL_BLOCK)
                .add(ModBlocks.YELLOW_CRYSTAL_BLOCK)
                .add(ModBlocks.BLUE_CRYSTAL_BLOCK)
                .add(ModBlocks.RED_CRYSTAL_BLOCK)
        ;

        //Trees
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.DRIFTWOOD_LOG)
                .add(ModBlocks.DRIFTWOOD_WOOD)
                .add(ModBlocks.STRIPPED_DRIFTWOOD_LOG)
                .add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD);
    }
}