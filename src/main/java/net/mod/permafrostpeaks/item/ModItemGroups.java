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
    public static final ItemGroup PERMAFROST_PEAKS_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PermaFrostPeaks.MOD_ID, "permafrost_peaks_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.ANTONIO_LIBERTO_BLOCK))
                    .displayName(Text.translatable("itemgroup.permafrostpeaks.permafrost_peaks_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RED_GARNET);
                    }).build());


    public static void registerItemGroups() {
        PermaFrostPeaks.LOGGER.info("Registering Item Groups for " + PermaFrostPeaks.MOD_ID);
    }
}
