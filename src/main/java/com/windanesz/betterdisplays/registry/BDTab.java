package com.windanesz.betterdisplays.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class BDTab {

	public static final CreativeTabs BETTER_DISPLAYS_TAB = new BDTAb("betterdisplays");

	private static class BDTAb extends CreativeTabs {
		public BDTAb(String label) {
			super(label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(Item.getItemFromBlock(BDBlocks.display_case));
		}
	}
}
