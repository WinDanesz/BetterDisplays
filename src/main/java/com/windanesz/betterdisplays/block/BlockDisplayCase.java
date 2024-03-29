package com.windanesz.betterdisplays.block;

import com.windanesz.betterdisplays.registry.BDTab;
import com.windanesz.betterdisplays.tileentity.TileEntityDisplayCase;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

public class BlockDisplayCase extends Block implements ITileEntityProvider {

	String tile;

	public BlockDisplayCase(String tile) {
		super(Material.WOOD);
		setCreativeTab(BDTab.BETTER_DISPLAYS_TAB);
		setSoundType(SoundType.GLASS);
		setHardness(2.0F);
		setResistance(5.0F);
		this.tile = tile;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public boolean isFullCube(IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return TileFactory.getTile(tile);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState block, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {

		ItemStack toInsert = player.getHeldItem(hand);

		TileEntity tileEntity = world.getTileEntity(pos);

		if (!(tileEntity instanceof TileEntityDisplayCase)) {
			return false;
		}

		TileEntityDisplayCase tileCase = (TileEntityDisplayCase) tileEntity;
		ItemStack currentStack = tileCase.getStack();

		if (player.isSneaking()) {

			if (!currentStack.isEmpty()) {
				tileCase.rotateItem(side);
			}

			return true;
		}

		Item item1 = toInsert.getItem();

		if (Item.getItemFromBlock(Blocks.GOLD_BLOCK) == item1 ||
				Item.getItemFromBlock(Blocks.IRON_BLOCK) == item1 ||
				Item.getItemFromBlock(Blocks.LAPIS_BLOCK) == item1 ||
				Item.getItemFromBlock(Blocks.DIAMOND_BLOCK) == item1 ||
				Item.getItemFromBlock(Blocks.REDSTONE_BLOCK) == item1 ||
				Item.getItemFromBlock(Blocks.EMERALD_BLOCK) == item1 ||
				Item.getItemFromBlock(Blocks.QUARTZ_BLOCK) == item1 ||
				Item.getItemFromBlock(Blocks.COAL_BLOCK) == item1 ||
				ItemStack.EMPTY != OreDictionary.getOres("plankWood").stream().filter(stack -> stack.getItem().getRegistryName().toString()
						.equals(toInsert.getItem().getRegistryName().toString())).findFirst().orElse(ItemStack.EMPTY)) {
			tileCase.setMainBlock(toInsert);
//			if (!player.isCreative()) { toInsert.shrink(1); }
			return true;
		} else if (item1 == Item.getItemFromBlock(Blocks.CARPET)) {
			tileCase.setCarpetBlock(toInsert);
//			if (!player.isCreative()) { toInsert.shrink(1); }
			return true;

		} else {
			if (ItemStack.EMPTY != OreDictionary.getOres("blockGlass").stream().filter(stack -> stack.getItem().getRegistryName().toString()
					.equals(toInsert.getItem().getRegistryName().toString())).findFirst().orElse(ItemStack.EMPTY)) {
//				giveStackToPlayer(player, new ItemStack(Item.getItemFromBlock(tileCase.getGlassBlock().getBlock()), 1, tileCase.getGlassBlock().getBlock().getMetaFromState(tileCase.getGlassBlock())));
				tileCase.setGlassBlock(toInsert);
//				if (!player.isCreative()) { toInsert.shrink(1); }
				return true;
			}
		}

		if (currentStack.isEmpty()) {
			ItemStack stack = toInsert.copy();
			stack.setCount(1);
			((TileEntityDisplayCase) tileEntity).setStack(stack);
			world.playSound(null, pos, SoundEvents.ENTITY_ITEMFRAME_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!player.isCreative()) { toInsert.shrink(1); }

		} else {

			if (toInsert.isEmpty()) {
				player.setHeldItem(hand, currentStack);
			} else if (!player.addItemStackToInventory(currentStack)) {
				world.playSound(null, pos, SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
				player.dropItem(currentStack, false);
			}

			((TileEntityDisplayCase) tileEntity).setStack(ItemStack.EMPTY);
		}

		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileEntityDisplayCase) {
			ItemStack stack = ((TileEntityDisplayCase) tileEntity).getStack();

			TileEntityDisplayCase displayCase = (TileEntityDisplayCase) tileEntity;

			if (!stack.isEmpty()) {
				InventoryHelper.spawnItemStack(worldIn, tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), stack);
			}

			if (displayCase.getMainBlock() != null && displayCase.getMainBlock() != Blocks.PLANKS.getDefaultState()) {
				IBlockState blockState = displayCase.getMainBlock();
				ItemStack itemStack = new ItemStack(Item.getItemFromBlock(blockState.getBlock()), 1, blockState.getBlock().getMetaFromState(blockState));
//				InventoryHelper.spawnItemStack(worldIn, tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), itemStack);
			}

			if (displayCase.getGlassBlock() != null && displayCase.getGlassBlock() != Blocks.GLASS.getDefaultState()) {
				IBlockState blockState = displayCase.getGlassBlock();
				ItemStack itemStack = new ItemStack(Item.getItemFromBlock(blockState.getBlock()), 1, blockState.getBlock().getMetaFromState(blockState));
//				InventoryHelper.spawnItemStack(worldIn, tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), itemStack);
			}

			if (displayCase.getCarpetBlock() != null) {
				IBlockState blockState = displayCase.getCarpetBlock();
				ItemStack itemStack = new ItemStack(Item.getItemFromBlock(blockState.getBlock()), 1, blockState.getBlock().getMetaFromState(blockState));
//				InventoryHelper.spawnItemStack(worldIn, tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), itemStack);
			}
		}

		super.breakBlock(worldIn, pos, state);
	}

	/**
	 * Static method to give an itemstack to a player. Handles side checks and null checks, prioritizes the hands.
	 *
	 * @param player the player who receives the item
	 * @param stack  the stack to give
	 * @return false if failed to give, true if successfully gave the item
	 */
	public static boolean giveStackToPlayer(EntityPlayer player, ItemStack stack) {
		if (player != null && stack != null && !stack.isEmpty()) {

			if (!player.world.isRemote) {

				if (player.getHeldItemMainhand().isEmpty()) {
					// main hand
					player.setHeldItem(EnumHand.MAIN_HAND, stack);
				} else if (player.getHeldItemOffhand().isEmpty()) {
					// offhand
					player.setHeldItem(EnumHand.OFF_HAND, stack);
				} else {
					// any slot
					if (!player.inventory.addItemStackToInventory(stack)) {
						// or just drop the item..
						player.dropItem(stack, false);
					}
				}

				return true;
			}
		}

		return false;
	}

}

