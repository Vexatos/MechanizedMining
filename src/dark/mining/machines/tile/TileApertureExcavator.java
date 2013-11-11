package dark.mining.machines.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import dark.api.events.MachineMiningEvent;
import dark.core.helpers.InvInteractionHelper;
import dark.core.helpers.ItemWorldHelper;
import dark.core.prefab.machine.TileEntityEnergyMachine;

/** @author Archadia */
public class TileApertureExcavator extends TileEntityEnergyMachine
{
    /** The class that interacts with inventories for this machine */
    private InvInteractionHelper invExtractionHelper;
    private int lastY = -1;

    public TileApertureExcavator()
    {
        super(0, 3);
    }

    /** Gets the class that managed extracting and placing items into inventories */
    public InvInteractionHelper invHelper()
    {
        if (invExtractionHelper == null || invExtractionHelper.world != this.worldObj)
        {
            this.invExtractionHelper = new InvInteractionHelper(this.worldObj, new Vector3(this), new ArrayList<ItemStack>(), false);
        }
        return invExtractionHelper;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (!worldObj.isRemote && this.isFunctioning())
        {
            if (this.ticks % 20 == 0)
            {
                excavate();
            }
        }
    }

    @Override
    public boolean canFunction()
    {
        //You need to turn it on with redstone in order for it to mine, this way the player has a chance to finish setting things up
        return super.canFunction() && this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    /** Tells the machine to dig down one block at a time */
    public void excavate()
    {
        if (lastY == -1)
        {
            lastY = this.yCoord - 1;
        }
        //Loop threw the entire y to make sure no new blocks were placed in the way
        for (int y = this.yCoord - 1; y > 0 && y < this.yCoord; y--)
        {
            Vector3 target = new Vector3(this.xCoord, y, this.zCoord);
            if (this.consumePower(1.5f, false))
            {
                Block block = Block.blocksList[target.getBlockID(this.worldObj)];
                //Check if we can mine as well send an event so other mods can cancel or mining attempt
                if (MachineMiningEvent.doMachineMiningCheck(this.worldObj, target, this))
                {
                    //Git blocks dropped from mining event to let other mods interact with the items drop. Eg ores will drop as rubble items from CM
                    List<ItemStack> items = MachineMiningEvent.getItemsMined(this.worldObj, target, this);
                    if (items != null)
                    {
                        for (ItemStack stack : items)
                        {
                            this.dropItems(stack);
                        }
                    }
                    //If we mine a block cancel set the blocks y as our last y value
                    worldObj.setBlockToAir(target.intX(), target.intY(), target.intZ());
                    this.consumePower(1.5f, true);
                } //If the block was not null yet the machine didn't mine it this mean we have to stop
                else if (block != null)
                {
                    break;
                }
                //Only go down one block at a time
                if (y < this.lastY)
                {
                    this.lastY = y;
                    break;
                }
            }
        }
    }

    /** Calls the the inv helper to drop the items */
    private void dropItems(ItemStack item)
    {
        if (item != null)
        {
            item = this.invHelper().storeItem(item, ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
            if (item != null)
            {
                this.invHelper().throwItem(new Vector3(this).modifyPositionFromSide(ForgeDirection.UP), item);
                item = null;
            }
        }
    }
}
