package net.mod.permafrostpeaks.entity.client;

import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.entity.custom.TrollEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TrollRenderer extends MobEntityRenderer<TrollEntity, TrollModel<TrollEntity>> {
    public TrollRenderer(EntityRendererFactory.Context context) {
        super(context, new TrollModel<>(context.getPart(TrollModel.TROLL)), 0.75f);
    }

    @Override
    public Identifier getTexture(TrollEntity entity) {
        return Identifier.of(PermaFrostPeaks.MOD_ID, "textures/entity/troll/troll.png");
    }

    @Override
    public void render(TrollEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.8f, 0.8f, 0.8f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}