package net.mod.permafrostpeaks.world.biome;

import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.OverworldBiomeCreator;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class ModBiomes {

    public static final RegistryKey<Biome> PERMAFROST_PEAKS = RegistryKey.of(
            RegistryKeys.BIOME,
            Identifier.of(PermaFrostPeaks.MOD_ID, "permafrost_peaks")
    );

    public static final RegistryKey<Biome> PERMAFROST_SPRUCE_TAIGA = RegistryKey.of(
            RegistryKeys.BIOME,
            Identifier.of(PermaFrostPeaks.MOD_ID, "permafrost_spruce_taiga")
    );

    public static void bootstrap(Registerable<Biome> context) {
        context.register(PERMAFROST_PEAKS, createPermafrostPeaks(context));
        context.register(PERMAFROST_SPRUCE_TAIGA, createPermafrostSpruceTaiga(context));
    }

    private static Biome createPermafrostSpruceTaiga(Registerable<Biome> context) {
        return OverworldBiomeCreator.createOldGrowthTaiga(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER),
                true // No clue why this here but it needed (should make stuff snowy, but it don't)
        );
    }

    private static Biome createPermafrostPeaks(Registerable<Biome> context) {

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        spawnSettings.spawn(SpawnGroup.CREATURE,
                new SpawnSettings.SpawnEntry(EntityType.GOAT, 5, 4, 6));

        GenerationSettings.LookupBackedBuilder generationSettings =
                new GenerationSettings.LookupBackedBuilder(
                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
                );

        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addEmeraldOre(generationSettings);

        BiomeEffects effects = new BiomeEffects.Builder()
                .waterColor(3750089)
                .waterFogColor(329011)
                .fogColor(12638463)
                .skyColor(8364543)
                .moodSound(BiomeMoodSound.CAVE)
                .build();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(-0.9f)
                .downfall(0.5f)
                .effects(effects)
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}