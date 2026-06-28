package net.mod.permafrostpeaks;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.*;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.block.entity.ModBlockEntities;
import net.mod.permafrostpeaks.entity.ModEntities;
import net.mod.permafrostpeaks.entity.custom.TrollEntity;
import net.mod.permafrostpeaks.item.ModItemGroups;
import net.mod.permafrostpeaks.item.ModItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.mod.permafrostpeaks.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermaFrostPeaks implements ModInitializer {
	public static final String MOD_ID = "permafrostpeaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

        //Item Related
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        //World Gen Related
        StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_LOG, ModBlocks.STRIPPED_DRIFTWOOD_LOG);
        StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_WOOD, ModBlocks.STRIPPED_DRIFTWOOD_WOOD);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LEAVES, 30, 60);

        //Entities
        ModEntities.registerModEntities();
        FabricDefaultAttributeRegistry.register(ModEntities.TROLL, TrollEntity.createAttributes());

        //Block Entities
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandlers();

        //Dimension
        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.GREEN_CRYSTAL_BLOCK)
                .lightWithItem(ModItems.GREEN_CRYSTAL_SHARD)
                .destDimID(Identifier.of(PermaFrostPeaks.MOD_ID, "permafrostpeaks"))
                .tintColor(2, 133, 0)
                .registerPortal();

        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.BLUE_CRYSTAL_BLOCK)
                .lightWithItem(ModItems.BLUE_CRYSTAL_SHARD)
                .destDimID(Identifier.of(PermaFrostPeaks.MOD_ID, "permafrostpeaks"))
                .tintColor(27, 24, 153)
                .registerPortal();

        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.RED_CRYSTAL_BLOCK)
                .lightWithItem(ModItems.RED_CRYSTAL_SHARD)
                .destDimID(Identifier.of(PermaFrostPeaks.MOD_ID, "permafrostpeaks"))
                .tintColor(181, 0, 0)
                .registerPortal();

        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.YELLOW_CRYSTAL_BLOCK)
                .lightWithItem(ModItems.YELLOW_CRYSTAL_SHARD)
                .destDimID(Identifier.of(PermaFrostPeaks.MOD_ID, "permafrostpeaks"))
                .tintColor(232, 209, 0)
                .registerPortal();

    }
}