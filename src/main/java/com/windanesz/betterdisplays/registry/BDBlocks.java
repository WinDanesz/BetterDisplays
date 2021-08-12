package com.windanesz.betterdisplays.registry;

import com.windanesz.betterdisplays.BetterDisplays;
import com.windanesz.betterdisplays.block.BlockBookHolder;
import com.windanesz.betterdisplays.block.BlockDisplayCase;
import com.windanesz.betterdisplays.block.BlockDisplayCaseSmall;
import com.windanesz.betterdisplays.block.BlockDisplayCaseSmallFramed;
import com.windanesz.betterdisplays.tileentity.TileEntityBookHolder;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCase;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseFramed;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseFramedNoTop;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseSmall;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCaseSmallFramed;
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
	public static final Block display_case_framed = placeholder();
	public static final Block display_case_framed_no_top = placeholder();
	public static final Block display_case_small = placeholder();
	public static final Block display_case_small_framed = placeholder();

	public static final Block book_holder_oak = placeholder();
	public static final Block book_holder_spruce = placeholder();
	public static final Block book_holder_birch = placeholder();
	public static final Block book_holder_acacia = placeholder();
	public static final Block book_holder_jungle = placeholder();
	public static final Block book_holder_dark_oak = placeholder();

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Block> event) {

		IForgeRegistry<Block> registry = event.getRegistry();

		registerBlock(registry, "display_case", new BlockDisplayCase("display_case"));
		registerBlock(registry, "display_case_framed", new BlockDisplayCase("display_case_framed"));
		registerBlock(registry, "display_case_framed_no_top", new BlockDisplayCase("display_case_framed_no_top"));
		registerBlock(registry, "display_case_small", new BlockDisplayCaseSmall("display_case_small"));
		registerBlock(registry, "display_case_small_framed", new BlockDisplayCaseSmallFramed( "display_case_small_framed"));

		registerBlock(registry, "book_holder_oak", new BlockBookHolder());
		registerBlock(registry, "book_holder_spruce", new BlockBookHolder());
		registerBlock(registry, "book_holder_birch", new BlockBookHolder());
		registerBlock(registry, "book_holder_acacia", new BlockBookHolder());
		registerBlock(registry, "book_holder_jungle", new BlockBookHolder());
		registerBlock(registry, "book_holder_dark_oak", new BlockBookHolder());

	}

	public static void registerBlock(IForgeRegistry<Block> registry, String name, Block block) {
		block.setRegistryName(BetterDisplays.MODID, name);
		block.setTranslationKey(block.getRegistryName().toString());
		registry.register(block);
	}


	/**
	 * Called from the preInit method in the main mod class to register all the tile entities.
	 */
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityDisplayCase.class, new ResourceLocation(BetterDisplays.MODID, "display_case"));
		GameRegistry.registerTileEntity(TileEntityDisplayCaseFramed.class, new ResourceLocation(BetterDisplays.MODID, "display_case_framed"));
		GameRegistry.registerTileEntity(TileEntityDisplayCaseFramedNoTop.class, new ResourceLocation(BetterDisplays.MODID, "display_case_framed_no_top"));
		GameRegistry.registerTileEntity(TileEntityDisplayCaseSmall.class, new ResourceLocation(BetterDisplays.MODID, "display_case_small"));
		GameRegistry.registerTileEntity(TileEntityDisplayCaseSmallFramed.class, new ResourceLocation(BetterDisplays.MODID, "display_case_small_framed"));
		GameRegistry.registerTileEntity(TileEntityBookHolder.class, new ResourceLocation(BetterDisplays.MODID, "book_holder"));
	}

}
