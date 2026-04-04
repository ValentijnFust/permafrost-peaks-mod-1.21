package net.mod.permafrostpeaks.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.block.ModBlocks;
import java.util.jar.Attributes;



public class ModItemGroups {
    public static final ItemGroup PERMAFROST_PEAKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PermaFrostPeaks.MOD_ID, "permafrost_peaks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.GOAT_FUR))
                    .displayName(Text.translatable("itemgroup.permafrostpeaks.permafrost_peaks"))
                    .entries((displayContext, entries) -> {

                        //Items
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RED_GARNET);
                        entries.add(ModItems.ANTONIO_LIBERTO);
                        entries.add(ModItems.GOAT_FUR);

                        //Blocks
                        entries.add(ModBlocks.GREEN_CRYSTAL_BLOCK);
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.ANTONIO_LIBERTO_BLOCK);
                        entries.add(ModBlocks.TESTING_BLOCK);

                    }).build());


    public static void registerItemGroups() {
        PermaFrostPeaks.LOGGER.info("Registering Item Groups for " + PermaFrostPeaks.MOD_ID);
    }
}
