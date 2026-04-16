package net.mod.permafrostpeaks;

import net.fabricmc.api.ModInitializer;

import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.item.ModItemGroups;
import net.mod.permafrostpeaks.item.ModItems;
import net.mod.permafrostpeaks.world.gen.ModWorldGeneration;
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
        ModWorldGeneration.generateModWorldGen();
	}
}