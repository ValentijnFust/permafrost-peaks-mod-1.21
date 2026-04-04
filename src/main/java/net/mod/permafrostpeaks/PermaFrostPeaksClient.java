package net.mod.permafrostpeaks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.mod.permafrostpeaks.block.ModBlocks;

public class PermaFrostPeaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TESTING_BLOCK, RenderLayer.getTranslucent());

    }
}
