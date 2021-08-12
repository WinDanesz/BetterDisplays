package com.windanesz.betterdisplays;

import com.windanesz.betterdisplays.client.gui.config.GuiConfigBetterDisplays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

@SuppressWarnings("unused")
public class BetterDisplaysGuiFactory implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraftInstance){
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories(){
		return null;
	}

	@Override
	public boolean hasConfigGui(){
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen){
		return new GuiConfigBetterDisplays(parentScreen);
	}
}