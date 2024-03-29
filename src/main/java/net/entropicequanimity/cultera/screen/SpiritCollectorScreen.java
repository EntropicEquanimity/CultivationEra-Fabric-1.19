package net.entropicequanimity.cultera.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.entropicequanimity.cultera.CultivationEraMod;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SpiritCollectorScreen extends HandledScreen<SpiritCollectorScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(CultivationEraMod.MOD_ID, "textures/gui/spirit_collector_basic_gui.png");
    public SpiritCollectorScreen(SpiritCollectorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
//        titleX = (backgroundWidth - textRenderer.getWidth((title)) / 2);
    }
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgress(matrices, x, y);
    }

    private void renderProgress(MatrixStack matrices, int x, int y) {
            drawTexture(matrices, x + 155, y + 12, 176, 0, 10, handler.getProgressScale());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
