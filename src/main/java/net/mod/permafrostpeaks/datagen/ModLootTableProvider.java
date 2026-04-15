package net.mod.permafrostpeaks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        //Blocks
        addDrop(ModBlocks.GREEN_CRYSTAL_BLOCK, multipleOreDrops(ModBlocks.GREEN_CRYSTAL_BLOCK, ModItems.GREEN_CRYSTAL_SHARD,1,4));
        addDrop(ModBlocks.YELLOW_CRYSTAL_BLOCK, multipleOreDrops(ModBlocks.YELLOW_CRYSTAL_BLOCK, ModItems.YELLOW_CRYSTAL_SHARD, 1,4));
        addDrop(ModBlocks.BLUE_CRYSTAL_BLOCK, multipleOreDrops(ModBlocks.BLUE_CRYSTAL_BLOCK, ModItems.BLUE_CRYSTAL_SHARD, 1,4));
        addDrop(ModBlocks.RED_CRYSTAL_BLOCK, multipleOreDrops(ModBlocks.RED_CRYSTAL_BLOCK, ModItems.RED_CRYSTAL_SHARD, 1,4 ));

        //Testing Blocks
        addDrop(ModBlocks.PINK_GARNET_BLOCK);
        addDrop(ModBlocks.TESTING_BLOCK);
        addDrop(ModBlocks.ANTONIO_LIBERTO_BLOCK);
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}