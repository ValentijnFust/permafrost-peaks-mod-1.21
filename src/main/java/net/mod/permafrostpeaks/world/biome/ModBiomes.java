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
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;



public class ModBiomes {

    public static final RegistryKey<Biome> FROZEN_PEAKS = RegistryKey.of(
            RegistryKeys.BIOME,
            Identifier.of(PermaFrostPeaks.MOD_ID, "frozen_peaks")
    );

    public static final RegistryKey<Biome> FROZEN_TAIGA = RegistryKey.of(
            RegistryKeys.BIOME,
            Identifier.of(PermaFrostPeaks.MOD_ID, "frozen_taiga")
    );

    public static void bootstrap(Registerable<Biome> context) {

        context.register(FROZEN_PEAKS, frozenPeaks(context));
        context.register(FROZEN_TAIGA, frozenTaiga(context));
    }

    private static Biome frozenPeaks(Registerable<Biome> context) {

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.LookupBackedBuilder generationSettings =
                new GenerationSettings.LookupBackedBuilder(
                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
                );

        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(-0.7f)
                .downfall(0.9f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(8103167)
                        .moodSound(BiomeMoodSound.CAVE)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    private static Biome frozenTaiga(Registerable<Biome> context) {

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);

        GenerationSettings.LookupBackedBuilder generationSettings =
                new GenerationSettings.LookupBackedBuilder(
                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
                );

        DefaultBiomeFeatures.addTaigaTrees(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);


        return new Biome.Builder()
                .precipitation(true)
                .temperature(-0.5f)
                .downfall(0.8f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(8103167)
                        .moodSound(BiomeMoodSound.CAVE)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}