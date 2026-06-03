package net.mod.permafrostpeaks.world.tree;

import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator DRIFTWOOD = new SaplingGenerator(PermaFrostPeaks.MOD_ID + ":driftwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DRIFTWOOD_KEY), Optional.empty());
}