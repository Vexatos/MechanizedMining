package dark.mining.machines.tile;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import universalelectricity.core.vector.Vector3;
import dark.core.prefab.machine.TileEntityEnergyMachine;

/**
 * @author Archadia
 *
 */
public class TileApertureExcavator extends TileEntityEnergyMachine {

	private Vector3 target;
	
	public TileApertureExcavator() {
		super(0, 3);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			if(this.ticks % 20 == 0) {
				 excavate();
			}
		}
	}	
	
	public void excavate() {
		if (target == null) {
			target = new Vector3(xCoord, yCoord, zCoord);
		}
		if(target.intY() > 0) {
			if(this.getEnergyStored() >= 1.5f) {
				target.translate(Vector3.DOWN());

				int blockID = target.getBlockID(this.worldObj);
				Block block = Block.blocksList[blockID];
				if (block != null && !block.isAirBlock(this.worldObj, target.intX(), target.intY(), target.intZ()) && block.getBlockHardness(this.worldObj, target.intX(), target.intY(), target.intZ()) >= 0) {
					worldObj.setBlockToAir(target.intX(), target.intY(), target.intZ());
					this.consumePower(1.5f, true);
					ItemStack drop = block.getBlockDropped(worldObj, target.intX(), target.intY() , target.intZ(), worldObj.getBlockMetadata(target.intX(), target.intY() , target.intZ()), 0).get(0);
					
					if(block != Block.waterMoving || block != Block.waterMoving || block != Block.lavaMoving || block != block.lavaStill) {
						dropItems(drop);
					}
				}
			}
		}
	}
	
	private void dropItems(ItemStack item) {
		ItemStack itemStack = item;

		if(findInventory() != null) {
			if(itemStack != null) {
				for (int i = 0; i < findInventory().getSizeInventory(); i++) {
					itemStack = this.addStackToInventory(i, findInventory(), itemStack);
					return;
				}
			}
		}
		if(!worldObj.isRemote) {
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord, zCoord, itemStack));
		}
	}

	public ItemStack addStackToInventory(int slotIndex, TileEntityChest inventory, ItemStack itemStack)
	{
		if(itemStack != null) {
			if (inventory.getSizeInventory() > slotIndex)
			{
				ItemStack stackInInventory = inventory.getStackInSlot(slotIndex);
	
				if (stackInInventory == null)
				{
					inventory.setInventorySlotContents(slotIndex, itemStack);
					if (inventory.getStackInSlot(slotIndex) == null)
					{
						return itemStack;
					}
					return null;
				}
				if(stackInInventory.isItemEqual(itemStack) && stackInInventory.isStackable())
				{
					stackInInventory = stackInInventory.copy();
					int stackLim = Math.min(inventory.getInventoryStackLimit(), itemStack.getMaxStackSize());
					int rejectedAmount = Math.max((stackInInventory.stackSize + itemStack.stackSize) - stackLim, 0);
					stackInInventory.stackSize = Math.min(Math.max((stackInInventory.stackSize + itemStack.stackSize - rejectedAmount), 0), inventory.getInventoryStackLimit());
					itemStack.stackSize = rejectedAmount;
					inventory.setInventorySlotContents(slotIndex, stackInInventory);
				}
			}
	
			if (itemStack.stackSize <= 0)
			{
				return null;
			}
		}

		return itemStack;
	}
	
	private TileEntityChest findInventory() {

		if (worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord - 1,
					yCoord, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord,
					yCoord + 1, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord,
					yCoord - 1, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord,
					yCoord, zCoord + 1);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord,
					yCoord, zCoord - 1);
		}
		return null;
	}
}
