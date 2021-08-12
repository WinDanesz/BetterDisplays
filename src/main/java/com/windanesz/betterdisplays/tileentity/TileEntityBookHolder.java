package com.windanesz.betterdisplays.tileentity;

import com.windanesz.betterdisplays.BetterDisplays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileEntityBookHolder extends TileEntity implements IInventory {

	private ItemStack book;

	public TileEntityBookHolder() {
		book = ItemStack.EMPTY;
	}

	public void setBook(ItemStack book) { this.book = book; }

	public ItemStack getBook() { return book; }

	public boolean hasBook() { return getBook().isEmpty(); }

	private String customName;

	// Tile Data Handling
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagCompound itemTag = new NBTTagCompound();
		book.writeToNBT(itemTag);
		nbt.setTag("book", itemTag);

		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagCompound itemTag = nbt.getCompoundTag("book");
		this.book = new ItemStack(itemTag);
	}

	public void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		world.scheduleBlockUpdate(pos, this.getBlockType(), 0, 0);
		markDirty();
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}

	private IBlockState getState() {
		return world.getBlockState(pos);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return getBook().isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return index == 0 ? getBook() : ItemStack.EMPTY;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (index == 0 && hasBook()) {
			if (count >= getBook().getCount()) {
				ItemStack itemstack = getBook();
				setBook(ItemStack.EMPTY);
				return itemstack;
			} else {
				return getBook().splitStack(count);
			}
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (index == 0) {
			ItemStack itemstack = this.book.copy();
			this.book = ItemStack.EMPTY;
			return itemstack;
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index == 0 && BetterDisplays.settings.isAllowedBookItem(stack)) {
			this.setBook(stack);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == 0 && BetterDisplays.settings.isAllowedBookItem(stack);
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.setBook(ItemStack.EMPTY);
	}

	@Override

	public String getName() {
		return this.hasCustomName() ? this.customName : "tile.betterdisplays:book_holder";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}

	public void setName(String name) {
		this.customName = name;
	}
}
