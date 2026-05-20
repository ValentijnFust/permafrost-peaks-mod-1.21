package net.mod.permafrostpeaks.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.entity.custom.MantisEntity;

    public class ModEntities {
        public static final EntityType<MantisEntity> MANTIS = Registry.register(Registries.ENTITY_TYPE,
                Identifier.of(PermaFrostPeaks.MOD_ID, "mantis"),
                EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE)
                        .dimensions(1f, 2.5f).build());


    public static void registerModEntities() {
        PermaFrostPeaks.LOGGER.info("Registering Mod Entities for " + PermaFrostPeaks.MOD_ID);
    }
}
