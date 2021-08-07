package com.windanesz.betterdisplays.client.renderer;

import com.windanesz.betterdisplays.client.model.ModelDisplayCase;
import com.windanesz.betterdisplays.client.model.ModelDisplayCaseSmall;

public class RenderDisplayCaseSmall extends RenderDisplayCase {

	public RenderDisplayCaseSmall() { }

	private final ModelDisplayCaseSmall model = new ModelDisplayCaseSmall();

	@Override
	public ModelDisplayCase getModel() {
		return model;
	}

	@Override
	public float getItemYOffset() { return 0.42f; }
}
