package com.windanesz.betterdisplays.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDisplayCaseFramedNoTop extends ModelDisplayCase {
	private final ModelRenderer dcase;
	private final ModelRenderer glass;
	private final ModelRenderer carpet;

	public ModelDisplayCaseFramedNoTop() {
		textureWidth = 16;
		textureHeight = 16;

		dcase = new ModelRenderer(this);
		dcase.setRotationPoint(0.0F, 24.0F, 0.0F);
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -16.0F, -1.0F, 0.0F, 16, 1, 16, 0.0F, false));
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -11.0F, -5.0F, 5.0F, 6, 4, 6, 0.0F, false));
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -16.0F, -15.0F, 0.0F, 1, 14, 1, 0.0F, false));
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -1.0F, -15.0F, 0.0F, 1, 14, 1, 0.0F, false));
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -1.0F, -15.0F, 15.0F, 1, 14, 1, 0.0F, true));
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -16.0F, -15.0F, 15.0F, 1, 14, 1, 0.0F, false));
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -15.0F, -16.0F, 0.0F, 14, 1, 1, 0.0F, false));
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -15.0F, -16.0F, 15.0F, 14, 1, 1, 0.0F, false));
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -16.0F, -16.0F, 0.0F, 1, 1, 16, 0.0F, false));
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -1.0F, -16.0F, 0.0F, 1, 1, 16, 0.0F, false));

		glass = new ModelRenderer(this);
		glass.setRotationPoint(-8.0F, 1.0F, 8.0F);
		glass.cubeList.add(new ModelBox(glass, 0, 0, -8.0F, 8.0F, 7.99F, 16, 14, 0, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 0, -8.0F, 8.0F, -7.99F, 16, 14, 0, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 0, -7.99F, 8.0F, -8.0F, 0, 14, 16, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 0, 7.99F, 8.0F, -8.0F, 0, 14, 16, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 0, -8.0F, 7.01F, -8.0F, 16, 0, 16, 0.0F, false));

		carpet = new ModelRenderer(this);
		carpet.setRotationPoint(0.0F, 24.0F, 0.0F);
		carpet.cubeList.add(new ModelBox(carpet, 0, 0, -16.0F, -1.01F, 0.0F, 16, 0, 16, 0.0F, false));
		carpet.cubeList.add(new ModelBox(carpet, 0, 0, -11.0F, -5.01F, 5.0F, 6, 0, 6, 0.0F, false));
		carpet.cubeList.add(new ModelBox(carpet, 0, 0, -11.0F, -5.0F, 4.99F, 6, 4, 0, 0.0F, false));
		carpet.cubeList.add(new ModelBox(carpet, 0, 0, -11.0F, -5.0F, 11.01F, 6, 4, 0, 0.0F, false));
		carpet.cubeList.add(new ModelBox(carpet, 0, 0, -5.99F, -5.0F, 5.0F, 1, 4, 6, 0.0F, false));
		carpet.cubeList.add(new ModelBox(carpet, 0, 0, -11.01F, -5.0F, 5.0F, 1, 4, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		//dcase.render(scale);
	}

	public void renderCase(float scale) {
		dcase.render(scale);
	}

	public void renderGlass(float scale) {
		glass.render(scale);
	}

	public void renderCarpet(float scale) { carpet.render(scale); }

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}