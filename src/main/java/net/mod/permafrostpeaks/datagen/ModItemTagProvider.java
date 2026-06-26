package net.mod.permafrostpeaks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.item.ModItems;
import net.mod.permafrostpeaks.util.ModTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                //Items
                .add(ModItems.GOAT_FUR)

                .add(ModItems.BLUE_CRYSTAL_SHARD)
                .add(ModItems.RED_CRYSTAL_SHARD)
                .add(ModItems.YELLOW_CRYSTAL_SHARD)
                .add(ModItems.GREEN_CRYSTAL_SHARD)

                .add(ModItems.BLUE_CRYSTAL_DUST)
                .add(ModItems.RED_CRYSTAL_DUST)
                .add(ModItems.YELLOW_CRYSTAL_DUST)
                .add(ModItems.GREEN_CRYSTAL_DUST)

                //Original Items
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.APPLE);

        //Armor
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.FUR_HELMET)
                .add(ModItems.FUR_CHESTPLATE)
                .add(ModItems.FUR_LEGGINGS)
                .add(ModItems.FUR_BOOTS);

        //Trees
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.DRIFTWOOD_LOG.asItem())
                .add(ModBlocks.DRIFTWOOD_WOOD.asItem())
                .add(ModBlocks.STRIPPED_DRIFTWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.DRIFTWOOD_PLANKS.asItem());
    }
}