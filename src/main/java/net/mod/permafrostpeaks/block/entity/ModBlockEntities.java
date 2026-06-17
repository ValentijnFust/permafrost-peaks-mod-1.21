package net.mod.permafrostpeaks.block.entity;

import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.block.entity.custom.CrystalCrusherBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<CrystalCrusherBlockEntity> CRYSTAL_CRUSHER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PermaFrostPeaks.MOD_ID, "crystal_crusher_be"),
                    BlockEntityType.Builder.create(CrystalCrusherBlockEntity::new, ModBlocks.CRYSTAL_CRUSHER).build(null));

    public static void registerBlockEntities() {
        PermaFrostPeaks.LOGGER.info("Registering Block Entities for " + PermaFrostPeaks.MOD_ID);
    }
}