package net.mod.permafrostpeaks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        // Block-Shard Recipes
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.GREEN_CRYSTAL_SHARD, RecipeCategory.MISC, ModBlocks.GREEN_CRYSTAL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.YELLOW_CRYSTAL_SHARD, RecipeCategory.MISC, ModBlocks.YELLOW_CRYSTAL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RED_CRYSTAL_SHARD, RecipeCategory.MISC, ModBlocks.RED_CRYSTAL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.BLUE_CRYSTAL_SHARD, RecipeCategory.MISC, ModBlocks.BLUE_CRYSTAL_BLOCK);

        // Fur Armor Recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FUR_HELMET)
                .pattern("FFF")
                .pattern("F F")
                .input('F', ModItems.GOAT_FUR)
                .criterion(hasItem(ModItems.GOAT_FUR), conditionsFromItem(ModItems.GOAT_FUR))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FUR_CHESTPLATE)
                .pattern("F F")
                .pattern("FFF")
                .pattern("FFF")
                .input('F', ModItems.GOAT_FUR)
                .criterion(hasItem(ModItems.GOAT_FUR), conditionsFromItem(ModItems.GOAT_FUR))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FUR_LEGGINGS)
                .pattern("FFF")
                .pattern("F F")
                .pattern("F F")
                .input('F', ModItems.GOAT_FUR)
                .criterion(hasItem(ModItems.GOAT_FUR), conditionsFromItem(ModItems.GOAT_FUR))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FUR_BOOTS)
                .pattern("F F")
                .pattern("F F")
                .input('F', ModItems.GOAT_FUR)
                .criterion(hasItem(ModItems.GOAT_FUR), conditionsFromItem(ModItems.GOAT_FUR))
                .offerTo(exporter);
    }
}