package com.windanesz.betterdisplays.block;

import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCase;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseFramed;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseFramedNoTop;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseSmall;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseSmallFramed;

public class TileFactory {

	public static TileEntityDisplayCase getTile(String tile) {
		if (tile == null) {
			return null;
		}

		if (tile.equalsIgnoreCase("display_case")) {
			return new TileEntityDisplayCase();
		} else if (tile.equalsIgnoreCase("display_case_framed")) {
			return new TileEntityDisplayCaseFramed();
		} else if (tile.equalsIgnoreCase("display_case_framed_no_top")) {
			return new TileEntityDisplayCaseFramedNoTop();
		} else if (tile.equalsIgnoreCase("display_case_small")) {
			return new TileEntityDisplayCaseSmall();
		} else if (tile.equalsIgnoreCase("display_case_small_framed")) {
			return new TileEntityDisplayCaseSmallFramed();
		}
		return null;
	}
}
