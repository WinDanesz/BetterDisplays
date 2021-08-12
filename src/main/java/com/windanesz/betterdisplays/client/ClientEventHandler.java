package com.windanesz.betterdisplays.client;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {

	public static BlockPos lastBlockPosResult = BlockPos.ORIGIN;

	@SubscribeEvent
	public static void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {
		if (event.getPlayer() != null) {
			if (event.getTarget() != null && event.getTarget().typeOfHit == RayTraceResult.Type.BLOCK) {
				lastBlockPosResult = event.getTarget().getBlockPos();
				return;
			}
		}

		lastBlockPosResult = BlockPos.ORIGIN;
	}
}
