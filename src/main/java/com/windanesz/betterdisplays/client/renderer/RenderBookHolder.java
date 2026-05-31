package com.windanesz.betterdisplays.client.renderer;

import com.windanesz.betterdisplays.BetterDisplays;
import com.windanesz.betterdisplays.client.ClientEventHandler;
import com.windanesz.betterdisplays.tileentity.TileEntityBookHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;

import java.util.List;

public class RenderBookHolder extends TileEntitySpecialRenderer<TileEntityBookHolder> {

	@Override
	public void render(TileEntityBookHolder tileentity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		ItemStack book = tileentity.getBook();
		if (book.isEmpty() || !isHovered(tileentity)) {
			return;
		}

		renderTooltip(tileentity, book, x, y, z);
	}

	private boolean isHovered(TileEntityBookHolder tileentity) {
		return ClientEventHandler.lastBlockPosResult != null && ClientEventHandler.lastBlockPosResult.equals(tileentity.getPos());
	}

	private void renderTooltip(TileEntityBookHolder tileentity, ItemStack book, double x, double y, double z) {
		if (!BetterDisplays.settings.displayItemNames) {
			return;
		}

		ITooltipFlag tooltipFlag = Minecraft.getMinecraft().gameSettings.advancedItemTooltips
				? ITooltipFlag.TooltipFlags.ADVANCED
				: ITooltipFlag.TooltipFlags.NORMAL;
		List<String> tooltipLines = book.getTooltip(Minecraft.getMinecraft().player, tooltipFlag);
		float yOffset = 1.08F;
		for (String tooltipLine : tooltipLines) {
			renderTooltipLine(tooltipLine, x, y + yOffset, z);
			yOffset -= 0.06F;
		}
	}

	private void renderTooltipLine(String text, double x, double y, double z) {
		FontRenderer fontRenderer = getFontRenderer();
		float scale = 0.006F;
		int width = fontRenderer.getStringWidth(text) / 2;

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y, (float) z + 0.5F);
		GlStateManager.rotate(-this.rendererDispatcher.entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(this.rendererDispatcher.entityPitch, 1.0F, 0.0F, 0.0F);
		GlStateManager.scale(-scale, -scale, scale);
		GlStateManager.disableLighting();
		GlStateManager.depthMask(false);
		GlStateManager.disableDepth();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
				GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);

		Tessellator tessellator = Tessellator.getInstance();
		net.minecraft.client.renderer.BufferBuilder buffer = tessellator.getBuffer();
		GlStateManager.disableTexture2D();
		buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		buffer.pos(-width - 1, -1, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
		buffer.pos(-width - 1, 8, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
		buffer.pos(width + 1, 8, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
		buffer.pos(width + 1, -1, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		fontRenderer.drawString(text, -width, 0, 0x20FFFFFF);
		GlStateManager.enableDepth();
		GlStateManager.depthMask(true);
		fontRenderer.drawString(text, -width, 0, 0xFFFFFFFF);
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();
	}
}
