package com.windanesz.betterdisplays.block;

import com.windanesz.betterdisplays.BetterDisplays;
import com.windanesz.betterdisplays.registry.BDTab;
import com.windanesz.betterdisplays.tileentity.TileEntityBookHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockBookHolder extends Block implements ITileEntityProvider {
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool HAS_BOOK = PropertyBool.create("has_book");

	protected static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	public BlockBookHolder() {
		super(Material.WOOD);
		setCreativeTab(BDTab.BETTER_DISPLAYS_TAB);
		setSoundType(SoundType.WOOD);
		setHardness(2.0F);
		setResistance(5.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HAS_BOOK, Boolean.valueOf(false)));

	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(HAS_BOOK, Boolean.valueOf(false));
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(HAS_BOOK, Boolean.valueOf((meta & 4) != 0)).withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3));
	}

	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();

		if (((Boolean) state.getValue(HAS_BOOK)).booleanValue()) {
			i |= 4;
		}

		return i;
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, HAS_BOOK});
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBookHolder();
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

		if (hand == EnumHand.MAIN_HAND) {
			ItemStack toInsert = player.getHeldItem(hand);

			TileEntity tileEntity = world.getTileEntity(pos);

			if (!(tileEntity instanceof TileEntityBookHolder)) {
				return true;
			}

			TileEntityBookHolder tileEntityBookHolder = (TileEntityBookHolder) tileEntity;
			ItemStack currentBook = tileEntityBookHolder.getBook();

			if (currentBook.isEmpty()) {
				if (!toInsert.isEmpty() && BetterDisplays.settings.isAllowedBookItem(toInsert)) {
					if (!world.isRemote) {
						ItemStack stack = toInsert.copy();
						stack.setCount(1);
						((TileEntityBookHolder) tileEntity).setBook(stack);
						world.playSound(null, pos, SoundEvents.ENTITY_ITEMFRAME_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
						world.setBlockState(pos, block.withProperty(HAS_BOOK, Boolean.valueOf(true)), 2);
						//						world.markBlockRangeForRenderUpdate(pos, pos);

						if (!player.isCreative()) { toInsert.shrink(1); }
						tileEntityBookHolder.sendUpdates();
						//					return true;
					}
				}
				return true;
			} else {
				if (!world.isRemote) {
					if (!player.addItemStackToInventory(currentBook)) {
						world.playSound(null, pos, SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
						player.dropItem(currentBook, false);
					}
					world.setBlockState(pos, block.withProperty(HAS_BOOK, Boolean.valueOf(false)), 2);
					//					world.markBlockRangeForRenderUpdate(pos, pos);

					((TileEntityBookHolder) tileEntity).setBook(ItemStack.EMPTY);
					//tileEntityBookHolder.markDirty();
					tileEntityBookHolder.sendUpdates();
				}
				return true;
			}
		}
		//		return true;

		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileEntityBookHolder) {
			TileEntityBookHolder display = (TileEntityBookHolder) tileEntity;

			ItemStack stack = display.getBook();

			if (!stack.isEmpty()) {
				InventoryHelper.spawnItemStack(worldIn, tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), stack);
			}
		}

		super.breakBlock(worldIn, pos, state);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

		switch (enumfacing) {
			case EAST:
				return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.15D, 1.0D, 1.0D);
			case WEST:
				return new AxisAlignedBB(1.0D, 0.0D, 0.0D, 0.85D, 1.0D, 1.0D);
			case SOUTH:
				return new AxisAlignedBB(1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.15D);
			case NORTH:
			default:
				return new AxisAlignedBB(0.0D, 0.0D, 0.85D, 1.0D, 1.0D, 1D);
		}
	}

}

