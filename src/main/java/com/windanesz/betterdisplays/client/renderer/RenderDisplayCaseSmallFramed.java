package com.windanesz.betterdisplays.client.renderer;

import com.windanesz.betterdisplays.client.model.ModelDisplayCase;
import com.windanesz.betterdisplays.client.model.ModelDisplayCaseSmallFramed;

public class RenderDisplayCaseSmallFramed extends RenderDisplayCase {

	public RenderDisplayCaseSmallFramed() { }

	private final ModelDisplayCaseSmallFramed model = new ModelDisplayCaseSmallFramed();

	@Override
	public ModelDisplayCase getModel() {
		return model;
	}

	@Override
	public float getItemYOffset() { return 0.42f; }
}
