package com.windanesz.betterdisplays.client.renderer;

import com.windanesz.betterdisplays.client.model.ModelDisplayCase;
import com.windanesz.betterdisplays.client.model.ModelDisplayCaseFramed;

public class RenderDisplayCaseFramed extends RenderDisplayCase {

	public RenderDisplayCaseFramed() { }

	private final ModelDisplayCaseFramed model = new ModelDisplayCaseFramed();

	@Override
	public ModelDisplayCase getModel() {
		return model;
	}
}
