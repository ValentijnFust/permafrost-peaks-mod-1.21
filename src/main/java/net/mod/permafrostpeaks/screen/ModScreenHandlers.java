package net.mod.permafrostpeaks.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.screen.custom.CrystalCrusherScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {

    public static final ScreenHandlerType<CrystalCrusherScreenHandler> CRYSTAL_CRUSHER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(PermaFrostPeaks.MOD_ID, "crystal_crusher_screen_handler"),
                    new ExtendedScreenHandlerType<>(CrystalCrusherScreenHandler::new, BlockPos.PACKET_CODEC));


    public static void registerScreenHandlers() {
        PermaFrostPeaks.LOGGER.info("Registering Screen Handlers for " + PermaFrostPeaks.MOD_ID);
    }
}