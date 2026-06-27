package net.mod.permafrostpeaks.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.entity.custom.TrollEntity;

    public class ModEntities {
        public static final EntityType<TrollEntity> TROLL = Registry.register(Registries.ENTITY_TYPE,
                Identifier.of(PermaFrostPeaks.MOD_ID, "troll"),
                EntityType.Builder.create(TrollEntity::new, SpawnGroup.CREATURE)
                        .dimensions(0.5f, 0.9f).build());


    public static void registerModEntities() {
        PermaFrostPeaks.LOGGER.info("Registering Mod Entities for " + PermaFrostPeaks.MOD_ID);
    }
}
