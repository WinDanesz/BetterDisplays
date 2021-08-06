package com.windanesz.betterdisplays.registry;

import com.windanesz.betterdisplays.BetterDisplays;
import com.windanesz.betterdisplays.block.BlockDisplayCase;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCase;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

@GameRegistry.ObjectHolder(BetterDisplays.MODID)
@Mod.EventBusSubscriber
public class BDBlocks {

	private BDBlocks() {} // no instances

	@Nonnull
	@SuppressWarnings("ConstantConditions")
	private static <T> T placeholder() { return null; }

	public static final Block display_case = placeholder();

	public static void registerBlock(IForgeRegistry<Block> registry, String name, Block block) {
		block.setRegistryName(BetterDisplays.MODID, name);
		block.setTranslationKey(block.getRegistryName().toString());
		registry.register(block);
	}

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Block> event) {

		IForgeRegistry<Block> registry = event.getRegistry();

		registerBlock(registry, "display_case", new BlockDisplayCase());

	}

	/**
	 * Called from the preInit method in the main mod class to register all the tile entities.
	 */
	public static void registerTileEntities() {
		//		GameRegistry.registerTileEntity(TileSphereCognizance.class, new ResourceLocation(AncientSpellcraft.MODID, "sphere_cognizance"));
		//		GameRegistry.registerTileEntity(TileSentinel.class, new ResourceLocation(AncientSpellcraft.MODID, "sentinel_block"));
		//
		GameRegistry.registerTileEntity(TileEntityDisplayCase.class, new ResourceLocation(BetterDisplays.MODID, "display_case"));
		//		GameRegistry.registerTileEntity(TileCandleLight.class, new ResourceLocation(AncientSpellcraft.MODID, "candlelight"));
		//		GameRegistry.registerTileEntity(TileSkullWatch.class, new ResourceLocation(AncientSpellcraft.MODID, "skull_watch"));
		//		GameRegistry.registerTileEntity(TileScribingDesk.class, new ResourceLocation(AncientSpellcraft.MODID, "scribing_desk"));
		//		GameRegistry.registerTileEntity(TileRune.class, new ResourceLocation(AncientSpellcraft.MODID, "placed_rune"));
		//		GameRegistry.registerTileEntity(TileEntityMagicMushroom.class, new ResourceLocation(AncientSpellcraft.MODID, "magic_mushroom_tile"));
		//
		//		GameRegistry.registerTileEntity(TileEntityRevertingBlock.class, new ResourceLocation(AncientSpellcraft.MODID, "reverting_tile"));
	}

}
