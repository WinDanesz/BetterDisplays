package com.windanesz.betterdisplays.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;

public class TileEntityDisplayCase extends TileEntity {

	private ItemStack stack;
	private int rotationX;
	private int rotationZ;

	private IBlockState mainBlock = Blocks.PLANKS.getDefaultState();
	private IBlockState glassBlock = Blocks.GLASS.getDefaultState();
	private IBlockState carpetBlock;


	public TileEntityDisplayCase() {
		stack = ItemStack.EMPTY;
		rotationX = 0;
		rotationZ = 0;
	}

	public void setStack(ItemStack stack) { this.stack = stack; }

	public ItemStack getStack() { return stack; }

	public void setXRotation(int rotation) { rotationX = rotation % 8; }

	public void setZRotation(int rotation) { rotationZ = rotation % 8; }

	public int getXRotation() { return rotationX; }

	public int getZRotation() { return rotationZ; }

	public void incrementXRotation() { setXRotation(getXRotation() + 1); }

	public void incrementZRotation() { setZRotation(getZRotation() + 1); }

	public void setMainBlock(ItemStack itemStack) {
		Block block = Block.getBlockFromItem(itemStack.getItem());
		mainBlock = block.getStateFromMeta(itemStack.getMetadata());
		world.notifyNeighborsRespectDebug(pos, this.blockType, true);
		markDirty();
	}

	public void setGlassBlock(ItemStack itemStack) {
		Block block = Block.getBlockFromItem(itemStack.getItem());
		glassBlock = block.getStateFromMeta(itemStack.getMetadata());
		world.notifyNeighborsRespectDebug(pos, this.blockType, true);
		markDirty();
	}

	public void setCarpetBlock(ItemStack itemStack) {
		Block block = Block.getBlockFromItem(itemStack.getItem());
		carpetBlock = block.getStateFromMeta(itemStack.getMetadata());
		world.notifyNeighborsRespectDebug(pos, this.blockType, true);
		markDirty();
	}

	public IBlockState getMainBlock() {
		return mainBlock;
	}

	public IBlockState getGlassBlock() {
		return glassBlock;
	}

	public IBlockState getCarpetBlock() {		return carpetBlock;	}

	public void rotateItem(EnumFacing side) {
		switch (side) {
			case EAST:
			case WEST:
				incrementZRotation();
				break;
			case NORTH:
			case SOUTH:
				incrementXRotation();
		}
	}

	// Tile Data Handling
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagCompound itemTag = new NBTTagCompound();
		stack.writeToNBT(itemTag);
		nbt.setTag("item", itemTag);
		nbt.setInteger("rotationX", rotationX);
		nbt.setInteger("rotationZ", rotationZ);

		if (mainBlock != null) {
			nbt.setString("main_block", mainBlock.getBlock().getRegistryName().toString());
			nbt.setInteger("main_block_meta", mainBlock.getBlock().getMetaFromState(mainBlock));
		}

		if (glassBlock != null) {
			nbt.setString("glass_block", glassBlock.getBlock().getRegistryName().toString());
			nbt.setInteger("glass_block_meta", glassBlock.getBlock().getMetaFromState(glassBlock));
		}

		if (carpetBlock != null) {
			nbt.setString("carpet_block", carpetBlock.getBlock().getRegistryName().toString());
			nbt.setInteger("carpet_block_meta", carpetBlock.getBlock().getMetaFromState(carpetBlock));
		}

		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagCompound itemTag = nbt.getCompoundTag("item");
		this.rotationX = nbt.getInteger("rotationX");
		this.rotationZ = nbt.getInteger("rotationZ");
		this.stack = new ItemStack(itemTag);

		String main_block = nbt.getString("main_block");
		if (!main_block.isEmpty()) {
			mainBlock = Block.getBlockFromName(main_block).getStateFromMeta(nbt.getInteger("main_block_meta"));
		}

		String glass_block = nbt.getString("glass_block");
		if (!glass_block.isEmpty()) {
			glassBlock = Block.getBlockFromName(glass_block).getStateFromMeta(nbt.getInteger("glass_block_meta"));
		}

		String carpet_block = nbt.getString("carpet_block");
		if (!glass_block.isEmpty()) {
			carpetBlock = Block.getBlockFromName(carpet_block).getStateFromMeta(nbt.getInteger("carpet_block_meta"));
		}

	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 0, this.getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}
}
