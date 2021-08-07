package com.windanesz.betterdisplays.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class TileEntityBookHolder extends TileEntity {

	private ItemStack book;

	public TileEntityBookHolder() {
		book = ItemStack.EMPTY;
	}

	public void setBook(ItemStack book) { this.book = book; }

	public ItemStack getBook() { return book; }

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
