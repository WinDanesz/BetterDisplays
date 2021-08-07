package com.windanesz.betterdisplays.client;

import com.windanesz.betterdisplays.CommonProxy;
import com.windanesz.betterdisplays.client.model.ItemColorizer;
import com.windanesz.betterdisplays.client.renderer.RenderDisplayCase;
import com.windanesz.betterdisplays.client.renderer.RenderDisplayCaseFramed;
import com.windanesz.betterdisplays.client.renderer.RenderDisplayCaseFramedNoTop;
import com.windanesz.betterdisplays.client.renderer.RenderDisplayCaseSmall;
import com.windanesz.betterdisplays.client.renderer.RenderDisplayCaseSmallFramed;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCase;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseFramed;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseFramedNoTop;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseSmall;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseSmallFramed;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	public void init() {

		// init colored items
		ItemColorizer.init();
	}

	public void registerRenderers() {

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayCase.class, new RenderDisplayCase());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayCaseFramed.class, new RenderDisplayCaseFramed());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayCaseFramedNoTop.class, new RenderDisplayCaseFramedNoTop());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayCaseSmall.class, new RenderDisplayCaseSmall());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayCaseSmallFramed.class, new RenderDisplayCaseSmallFramed());
	}
}