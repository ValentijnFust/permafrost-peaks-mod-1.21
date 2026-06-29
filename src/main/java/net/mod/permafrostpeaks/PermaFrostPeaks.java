package net.mod.permafrostpeaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.*;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.util.Identifier;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.block.entity.ModBlockEntities;
import net.mod.permafrostpeaks.entity.ModEntities;
import net.mod.permafrostpeaks.entity.custom.TrollEntity;
import net.mod.permafrostpeaks.item.ModItemGroups;
import net.mod.permafrostpeaks.item.ModItems;
import net.mod.permafrostpeaks.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermaFrostPeaks implements ModInitializer {
	public static final String MOD_ID = "permafrostpeaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final RegistryKey<World> PERMAFROST_DIMENSION_KEY = RegistryKey.of(
            RegistryKeys.WORLD,
            Identifier.of(MOD_ID, "permafrostpeaks")
    );

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

        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            if (!destination.getRegistryKey().equals(PERMAFROST_DIMENSION_KEY)) {
                return;
            }

            movePlayerToPermafrostSurface(player, destination);
        });
    }

    private static void movePlayerToPermafrostSurface(ServerPlayerEntity player, ServerWorld world) {
        int x = player.getBlockX();
        int z = player.getBlockZ();

        // Make sure the chunk is loaded before asking the heightmap.
        world.getChunk(x >> 4, z >> 4);

        int surfaceY = world.getTopY(
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                x,
                z
        );

        BlockPos safePos = findSafeSurfacePosition(world, x, surfaceY, z);

        player.teleport(
                world,
                safePos.getX() + 0.5,
                safePos.getY(),
                safePos.getZ() + 0.5,
                player.getYaw(),
                player.getPitch()
        );
    }

    private static BlockPos findSafeSurfacePosition(ServerWorld world, int x, int startY, int z) {
        // First try around the heightmap result.
        for (int y = startY + 4; y >= world.getBottomY(); y--) {
            BlockPos feet = new BlockPos(x, y, z);
            BlockPos head = feet.up();
            BlockPos ground = feet.down();

            if (
                    world.getBlockState(ground).isSolidBlock(world, ground)
                            && world.getBlockState(feet).isAir()
                            && world.getBlockState(head).isAir()
            ) {
                return feet;
            }
        }

        // Fallback: if somehow no safe surface was found, place the player high.
        return new BlockPos(x, startY + 2, z);
    }
}