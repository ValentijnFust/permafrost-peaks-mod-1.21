package net.mod.permafrostpeaks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.mod.permafrostpeaks.block.ModBlocks;
import net.mod.permafrostpeaks.entity.ModEntities;
import net.mod.permafrostpeaks.entity.client.MantisModel;
import net.mod.permafrostpeaks.entity.client.MantisRenderer;
import net.mod.permafrostpeaks.screen.ModScreenHandlers;
import net.mod.permafrostpeaks.screen.custom.CrystalCrusherScreen;

public class PermaFrostPeaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Block Transparency
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TESTING_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEN_CRYSTAL_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_CRYSTAL_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RED_CRYSTAL_BLOCK, RenderLayer.getTranslucent());

        //Sapling
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DRIFTWOOD_SAPLING, RenderLayer.getCutout());

        //Block Entities
        HandledScreens.register(ModScreenHandlers.CRYSTAL_CRUSHER_SCREEN_HANDLER, CrystalCrusherScreen::new);

        //Entity Rendering
        EntityModelLayerRegistry.registerModelLayer(MantisModel.MANTIS, MantisModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MANTIS, MantisRenderer::new);
    }
}
