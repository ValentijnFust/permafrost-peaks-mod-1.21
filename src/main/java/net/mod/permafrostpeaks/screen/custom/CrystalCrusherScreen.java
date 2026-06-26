package net.mod.permafrostpeaks.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CrystalCrusherScreen extends HandledScreen<CrystalCrusherScreenHandler> {

    private static final Identifier GUI_TEXTURE =
            Identifier.of(PermaFrostPeaks.MOD_ID, "textures/gui/crystal_crusher/crystal_crusher_gui.png");

    private static final Identifier CRUSHING_WHEEL_TEXTURE =
            Identifier.of(PermaFrostPeaks.MOD_ID, "textures/gui/crushing_wheel_progress.png");

    private static final Identifier LIT_PROGRESS =
            Identifier.of(PermaFrostPeaks.MOD_ID, "textures/gui/lit_progress.png");

    public CrystalCrusherScreen(CrystalCrusherScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderFuel(context, x, y);
        renderProgressCrushingWheels(context, x, y);
    }

    private void renderProgressCrushingWheels(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            int progress = handler.getScaledCrushingWheelsProgress(); // 0–17

            context.drawTexture(
                    CRUSHING_WHEEL_TEXTURE,
                    x + 93,
                    y + 31,
                    0,
                    0,
                    38,
                    progress,
                    38,
                    17
            );
        }
    }


    private void renderFuel(DrawContext context, int x, int y) {
        if (handler.isBurning()) {
            int scaled = handler.getScaledFuelProgress();

            context.drawTexture(
                    LIT_PROGRESS,
                    x + 55, y + 38 - scaled,
                    0, 14 - scaled,
                    14, scaled,
                    14, 14
            );
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}