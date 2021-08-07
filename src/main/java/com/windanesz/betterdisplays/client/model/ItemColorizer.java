package com.windanesz.betterdisplays.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemColorizer {

	private ItemColorizer() {} // no instances!

	public static void init() {
		ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
		IItemColor itemColorForOneLayer = (stack, tintIndex) -> {
			if (tintIndex == 1) {
//				return ItemRelic.getColorFromPotion(stack, tintIndex);
			}
			return 0xFFFFFF;
		};

//		itemColors.registerItemColorHandler(itemColorForOneLayer, BDItems.stone_tablet_grand);
	}

}
