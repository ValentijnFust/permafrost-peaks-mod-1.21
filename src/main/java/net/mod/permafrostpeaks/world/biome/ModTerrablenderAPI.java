package net.mod.permafrostpeaks.world.biome;

import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.world.biome.surface.ModMaterialRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(
                Identifier.of(PermaFrostPeaks.MOD_ID, "overworld"), 4));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, PermaFrostPeaks.MOD_ID, ModMaterialRules.makeRules());
    }
}