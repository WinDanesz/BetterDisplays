package com.windanesz.betterdisplays.client.gui.config;

import com.windanesz.betterdisplays.BetterDisplays;
import com.windanesz.betterdisplays.Settings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class GuiConfigBetterDisplays extends GuiConfig {

	public GuiConfigBetterDisplays(GuiScreen parent) {

		super(parent, getConfigEntries(), BetterDisplays.MODID, false, false,
				BetterDisplays.NAME + " - " + I18n.format("config." + BetterDisplays.MODID + ":title.general"));

	}

	private static List<IConfigElement> getConfigEntries() {

		List<IConfigElement> configList = new ArrayList<>(1);

		configList.add(new DummyCategoryElement("mainConfig", "config." + BetterDisplays.MODID + ":category." + Settings.MAIN_CONFIG, Category.class));

		configList.addAll(new ConfigElement(BetterDisplays.settings.getConfigCategory(Configuration.CATEGORY_GENERAL)).getChildElements());

		return configList;
	}

	// The reason this system is so convoluted is that it's designed for use with the @Config annotation. The problem is,
	// I'm not sure whether that will play well with the load phases. Hmmm...

	public static abstract class CategoryBase extends CategoryEntry {

		public CategoryBase(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected GuiScreen buildChildScreen() {

			String category = this.getCategory();

			// This GuiConfig object specifies the configID of the object and as such will force-save when it is closed.
			// The parent GuiConfig object's entryList will also be refreshed to reflect the changes.
			GuiConfig childScreen = new GuiConfig(this.owningScreen,
					(new ConfigElement(BetterDisplays.settings.getConfigCategory(category))).getChildElements(),
					this.owningScreen.modID, category, false, false,
					BetterDisplays.NAME + " - " + I18n.format("config." + BetterDisplays.MODID + ":title." + category));

//			childScreen.titleLine2 = I18n.format("config." + BetterDisplays.MODID + ":subtitle." + category);

			return childScreen;
		}

		protected abstract String getCategory();
	}

	/**
	 * Tweaks category of the config gui.
	 */
	public static class Category extends CategoryBase {

		public Category(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected String getCategory() { return Settings.MAIN_CONFIG; }
	}

}
