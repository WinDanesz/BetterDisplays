package com.windanesz.betterdisplays.registry;

import com.windanesz.betterdisplays.BetterDisplays;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

@ObjectHolder(BetterDisplays.MODID)
@Mod.EventBusSubscriber
public final class BDItems {

	private BDItems() {} // No instances!

	@Nonnull
	@SuppressWarnings("ConstantConditions")
	private static <T> T placeholder() { return null; }

//	public static final Item ancient_spellcraft_spell_book = placeholder();
//	public static final Item ancient_spell_book = placeholder();

	public static final Item rune_tiwaz = placeholder();


	// below registry methods are courtesy of EB
	public static void registerItem(IForgeRegistry<Item> registry, String name, Item item) {
		registerItem(registry, name, item, false);
	}

	public static void registerItem(IForgeRegistry<Item> registry, String name, Item item, boolean setTabIcon) {
		item.setRegistryName(BetterDisplays.MODID, name);
		item.setTranslationKey(item.getRegistryName().toString());
		registry.register(item);
	}

	private static void registerItemBlock(IForgeRegistry<Item> registry, Block block) {
		Item itemblock = new ItemBlock(block).setRegistryName(block.getRegistryName());
		registry.register(itemblock);
	}

	private static void registerItemBlock(IForgeRegistry<Item> registry, Block block, Item itemblock) {
		itemblock.setRegistryName(block.getRegistryName());
		registry.register(itemblock);
	}

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Item> event) {

		IForgeRegistry<Item> registry = event.getRegistry();

		registerItemBlock(registry, BDBlocks.display_case);
		// registerItem(registry, "ring_poison_arrow", new ItemASArtefact(EnumRarity.UNCOMMON, ItemArtefact.Type.RING));

	}


}