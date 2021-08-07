package com.windanesz.betterdisplays.client.renderer;

import com.windanesz.betterdisplays.client.model.ModelDisplayCase;
import com.windanesz.betterdisplays.client.model.ModelDisplayCaseFramedNoTop;

public class RenderDisplayCaseFramedNoTop extends RenderDisplayCase {

	public RenderDisplayCaseFramedNoTop() { }

	private final ModelDisplayCaseFramedNoTop model = new ModelDisplayCaseFramedNoTop();

	@Override
	public ModelDisplayCase getModel() {
		return model;
	}
}
