package net.mod.permafrostpeaks.world.dimension;

import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> PERMAFROSTPEAKS_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(PermaFrostPeaks.MOD_ID, "permafrostpeaks"));
    public static final RegistryKey<World> PERMAFROSTPEAKS_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(PermaFrostPeaks.MOD_ID, "permafrostpeaks"));
    public static final RegistryKey<DimensionType> PERMAFROSTPEAKS_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(PermaFrostPeaks.MOD_ID, "permafrostpeaks_type"));
    public static void bootstrap(Registerable<DimensionType> context) {
        context.register(PERMAFROSTPEAKS_TYPE, new DimensionType(
                OptionalLong.of(37000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }
}