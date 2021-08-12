package com.windanesz.betterdisplays;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Settings {

	public static final String MAIN_CONFIG = "settings";
	private Configuration config;

	public String[] whitelistedBookItems = {};
	public boolean displayItemNames = true;
	public boolean displayItemNamesOnlyWhileSneaking = false;

	void initConfig(FMLPreInitializationEvent event) {

		config = new Configuration(new File(BetterDisplays.configDirectory, BetterDisplays.MODID + ".cfg"));
		config.load();

		BetterDisplays.logger.info("Setting up main config");

		setupConfig();

		config.save();
	}

	/**
	 * Called to save changes to the config file after it has been edited in game from the menus.
	 */
	void saveConfigChanges() {

		BetterDisplays.logger.info("Saving in-game config changes");
		setupConfig();

		config.save();
	}

	public ConfigCategory getConfigCategory(String name) {
		return config.getCategory(name);
	}

	private void setupConfig() {

		List<String> propOrder = new ArrayList<>();

		Property property;

		config.addCustomCategoryComment(MAIN_CONFIG, "Settings of the Better Displays mod");

		property = config.get(MAIN_CONFIG, "whitelistedBookItems", new String[] {"antiqueatlas:antique_atlas"},
				"List of items which are allowed to be inserted into the Book Holder block.");
		property.setLanguageKey("config." + BetterDisplays.MODID + ".whitelisted_book_items");
		property.setRequiresMcRestart(false);
		whitelistedBookItems = property.getStringList();
		propOrder.add(property.getName());

		property = config.get(MAIN_CONFIG, "displayItemNames", true,
				"If true, Display Cases and Book Holders will display the name of the item they contain when the player looks at them.");
		property.setLanguageKey("config." + BetterDisplays.MODID + ".display_item_names");
		property.setRequiresMcRestart(false);
		displayItemNames = property.getBoolean();
		propOrder.add(property.getName());

		property = config.get(MAIN_CONFIG, "displayItemNamesOnlyWhileSneaking", true,
				"If true, Display Cases and Book Holders will only display the name of the item they contain when the player looks at them while sneaking.");
		property.setLanguageKey("config." + BetterDisplays.MODID + ".display_item_names_only_while_sneaking");
		property.setRequiresMcRestart(false);
		displayItemNamesOnlyWhileSneaking = property.getBoolean();
		propOrder.add(property.getName());

		config.setCategoryPropertyOrder(MAIN_CONFIG, propOrder);

	}

	public boolean isAllowedBookItem(ItemStack itemStack) {
		if (!itemStack.isEmpty()) {
			String registryName = itemStack.getItem().getRegistryName().toString().toLowerCase();
			String itemName = itemStack.getItem().getRegistryName().getPath();

			// Convert String Array to List
			List<String> list = Arrays.asList(BetterDisplays.settings.whitelistedBookItems);

			if (list.contains(registryName)) {
				return true;
			}

			Pattern pattern = Pattern.compile("(?!.*book_holder.*)(book|tome|codex|compendium|guide|journal|lexicon)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(itemName);
			if (matcher.find()) {
				return true;
			}
		}
		return false;
	}

}
