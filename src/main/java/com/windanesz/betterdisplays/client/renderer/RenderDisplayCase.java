
package com.windanesz.betterdisplays.client.renderer;

import com.windanesz.betterdisplays.client.model.ModelDisplayCase;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import java.util.StringTokenizer;

public class RenderDisplayCase extends TileEntitySpecialRenderer<TileEntityDisplayCase> {

	private static final float SCALE = 0.0625F;

	public ModelDisplayCase getModel() {
		return model;
	}

	private final ModelDisplayCase model = new ModelDisplayCase();

	private ResourceLocation cachedMainTexture = new ResourceLocation("minecraft:textures/blocks/planks_oak.png");
	private ResourceLocation cachedGlassTexture = new ResourceLocation("minecraft:textures/blocks/glass.png");

	private IBlockState cachedMainBlockState = Blocks.PLANKS.getDefaultState();
	private IBlockState cachedGlassBlockState = Blocks.GLASS.getDefaultState();

	public RenderDisplayCase() {}

	@Override
	public void render(TileEntityDisplayCase tileentity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();

		GlStateManager.translate((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);

		int rotX = tileentity.getXRotation();
		int rotZ = tileentity.getZRotation();

		// rotating Z otherwise items are rendered upside down
		GlStateManager.rotate(180, 0F, 0F, 1.0F);
		GlStateManager.rotate(180, 0F, 0F, 1.0F);
		GlStateManager.rotate(180, 0F, 0F, 1.0F);

		GlStateManager.pushMatrix();
		GlStateManager.translate(0f, getItemYOffset(), 0f);

		if (rotX != 0) {
			GlStateManager.rotate((float) rotX * 360.0F / 8.0F, 1.0F, 0.0F, 0.0F);
		}
		if (rotZ != 0) {
			GlStateManager.rotate((float) rotZ * 360.0F / 8.0F, 0.0F, 0.0F, 1.0F);
		}

		float t = Minecraft.getMinecraft().player.ticksExisted + partialTicks;
		// GlStateManager.translate(0, 0.05f * MathHelper.sin(t / 15), 0);
		this.renderItem(tileentity, 1);

		GlStateManager.popMatrix();
		GlStateManager.translate(+0.5f, -1F, -0.5f);

		BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		//		GlStateManager.disableLighting();

		GlStateManager.pushMatrix();

//		if (cachedMainBlockState != tileentity.getMainBlock()) {
			IBlockState cachedMainBlockState = tileentity.getMainBlock();

			String mainMaterialString = dispatcher.getModelForState(cachedMainBlockState).getQuads(cachedMainBlockState, EnumFacing.NORTH, 0).get(0).getSprite().getIconName();
			StringTokenizer stringTokenizerMainMaterial = new StringTokenizer(mainMaterialString, ":");
			if (stringTokenizerMainMaterial.countTokens() == 2) {
				String namespace = stringTokenizerMainMaterial.nextToken();
				String path = namespace + ":textures/" + stringTokenizerMainMaterial.nextToken() + ".png";
				ResourceLocation MainTexture = new ResourceLocation(path);
				this.bindTexture(MainTexture);
				this.getModel().renderCase(SCALE);
				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();
			}
//		}

		IBlockState cachedCarpetBlockState = tileentity.getCarpetBlock();
		String carpetString = dispatcher.getModelForState(cachedCarpetBlockState).getQuads(cachedCarpetBlockState, EnumFacing.NORTH, 0).get(0).getSprite().getIconName();
		StringTokenizer stringTokenizerCarpet = new StringTokenizer(carpetString, ":");
		if (stringTokenizerCarpet.countTokens() == 2) {
			String namespace = stringTokenizerCarpet.nextToken();
			String path = namespace + ":textures/" + stringTokenizerCarpet.nextToken() + ".png";
			ResourceLocation carpetTexture = new ResourceLocation(path);
			this.bindTexture(carpetTexture);
			GlStateManager.enableDepth();
			GlStateManager.depthFunc(515);
			GlStateManager.depthMask(true);
			GlStateManager.enableBlend(); // enable glass transparency
			this.getModel().renderCarpet(SCALE);
			GlStateManager.disableBlend(); // disable transparency
		}
//		if (cachedGlassBlockState != tileentity.getGlassBlock()) {
			IBlockState cachedGlassBlockState = tileentity.getGlassBlock();

			String glassString = dispatcher.getModelForState(cachedGlassBlockState).getQuads(cachedGlassBlockState, EnumFacing.NORTH, 0).get(0).getSprite().getIconName();
			StringTokenizer stringTokenizerGlass = new StringTokenizer(glassString, ":");
			if (stringTokenizerGlass.countTokens() == 2) {
				String namespace = stringTokenizerGlass.nextToken();
				String path = namespace + ":textures/" + stringTokenizerGlass.nextToken() + ".png";
				ResourceLocation GlassTexture = new ResourceLocation(path);
			this.bindTexture(GlassTexture);
				GlStateManager.enableDepth();
				GlStateManager.depthFunc(515);
				GlStateManager.depthMask(true);
			GlStateManager.enableBlend(); // enable glass transparency
			this.getModel().renderGlass(SCALE);
			GlStateManager.disableBlend(); // disable transparency
			}

//		}


		//		GlStateManager.enableLighting();
		GlStateManager.popMatrix();

		GlStateManager.popMatrix();
	}

	private void renderItem(TileEntityDisplayCase tileentity, float t) {

		ItemStack stack = tileentity.getStack();

		if (!stack.isEmpty()) {

			GlStateManager.pushMatrix();

			GlStateManager.rotate(180, 1, 0, 0);
			GlStateManager.rotate(t, 0, 1, 0);
			GlStateManager.scale(0.85F, 0.85F, 0.85F);

			Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.FIXED);

			GlStateManager.popMatrix();
		}
	}

	public float getItemYOffset() {
		return -0.02f;
	}

}
