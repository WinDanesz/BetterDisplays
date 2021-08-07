package com.windanesz.betterdisplays.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDisplayCaseSmall_bak extends ModelDisplayCase {
	private final ModelRenderer dcase;
	private final ModelRenderer glass;
	private final ModelRenderer carpet;

	public ModelDisplayCaseSmall_bak() {
		textureWidth = 16;
		textureHeight = 16;

		dcase = new ModelRenderer(this);
		dcase.setRotationPoint(0.0F, 24.0F, 0.0F);
		dcase.cubeList.add(new ModelBox(dcase, 0, 0, -16.0F, -1.0F, 0.0F, 16, 1, 16, 0.0F, false));

		glass = new ModelRenderer(this);
		glass.setRotationPoint(-8.0F, 1.0F, 8.0F);
		glass.cubeList.add(new ModelBox(glass, 0, 0, -8.0F, 15.0F, 7.99F, 16, 7, 0, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 0, -8.0F, 15.0F, -7.99F, 16, 7, 0, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 0, -7.99F, 15.0F, -8.0F, 0, 7, 16, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 0, 7.99F, 15.0F, -8.0F, 0, 7, 16, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 0, -8.0F, 15.01F, -8.0F, 16, 0, 16, 0.0F, false));

		carpet = new ModelRenderer(this);
		carpet.setRotationPoint(0.0F, 24.0F, 0.0F);
		carpet.cubeList.add(new ModelBox(carpet, 0, 0, -16.0F, -1.01F, 0.0F, 16, 0, 16, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		dcase.render(f5);
		glass.render(f5);
		carpet.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}