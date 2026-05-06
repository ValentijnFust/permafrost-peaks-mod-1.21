package net.mod.permafrostpeaks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.mod.permafrostpeaks.item.ModItems;
import net.mod.permafrostpeaks.util.ModTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

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
                .add(ModItems.GREEN_CRYSTAL_SHARD)
                .add(ModItems.YELLOW_CRYSTAL_SHARD)
                .add(ModItems.BLUE_CRYSTAL_SHARD)
                .add(ModItems.RED_CRYSTAL_SHARD)

                //Testing Items
                .add(ModItems.PINK_GARNET)
                .add(ModItems.RED_GARNET)
                .add(ModItems.ANTONIO_LIBERTO)

                //Original Items
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.APPLE);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PINK_GARNET_HELMET)
                .add(ModItems.PINK_GARNET_CHESTPLATE)
                .add(ModItems.PINK_GARNET_LEGGINGS)
                .add(ModItems.PINK_GARNET_BOOTS);
    }
}