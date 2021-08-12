package com.windanesz.betterdisplays.model;

import com.windanesz.betterdisplays.registry.BDBlocks;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public final class BDModels {
	private BDModels() { // no instances
	}

	@SubscribeEvent
	public static void register(ModelRegistryEvent event) {
//		registerItemModel(BDItems.ancient_spellcraft_spell_book);
//		registerItemModel(BDItems.ancient_spell_book);


		// Itemblocks
		registerItemModel(Item.getItemFromBlock(BDBlocks.display_case));
		registerItemModel(Item.getItemFromBlock(BDBlocks.display_case_framed));
		registerItemModel(Item.getItemFromBlock(BDBlocks.display_case_framed_no_top));
		registerItemModel(Item.getItemFromBlock(BDBlocks.display_case_small));
		registerItemModel(Item.getItemFromBlock(BDBlocks.display_case_small_framed));

		registerItemModel(Item.getItemFromBlock(BDBlocks.book_holder_oak));
		registerItemModel(Item.getItemFromBlock(BDBlocks.book_holder_spruce));
		registerItemModel(Item.getItemFromBlock(BDBlocks.book_holder_birch));
		registerItemModel(Item.getItemFromBlock(BDBlocks.book_holder_acacia));
		registerItemModel(Item.getItemFromBlock(BDBlocks.book_holder_jungle));
		registerItemModel(Item.getItemFromBlock(BDBlocks.book_holder_dark_oak));

	}

	// below registry methods are courtesy of EB
	private static void registerItemModel(Item item) {
		ModelBakery.registerItemVariants(item, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		ModelLoader.setCustomMeshDefinition(item, s -> new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

//	private static <T extends Item & IMultiTexturedItem> void registerMultiTexturedModel(T item) {
//
//		if (item.getHasSubtypes()) {
//			NonNullList<ItemStack> items = NonNullList.create();
//			item.getSubItems(item.getCreativeTab(), items);
//			for (ItemStack stack : items) {
//				ModelLoader.setCustomModelResourceLocation(item, stack.getMetadata(),
//						new ModelResourceLocation(item.getModelName(stack), "inventory"));
//			}
//		}
//	}

	private static void registerItemModel(Item item, int metadata, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(item.getRegistryName(), variant));
	}

}

