package com.windanesz.betterdisplays.client;

import com.windanesz.betterdisplays.CommonProxy;
import com.windanesz.betterdisplays.client.model.ItemColorizer;
import com.windanesz.betterdisplays.client.renderer.RenderDisplayCase;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCase;
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
	}
}