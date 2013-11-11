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

    private Vector3 target;

    /** The class that interacts with inventories for this machine */
    private InvInteractionHelper invExtractionHelper;

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
        if (!worldObj.isRemote)
        {
            if (this.ticks % 20 == 0)
            {
                excavate();
            }
        }
    }

    public void excavate()
    {
        if (target == null)
        {
            target = new Vector3(xCoord, yCoord - 1, zCoord);
        }
        if (target.intY() > 0)
        {
            if (this.consumePower(1.5f, false))
            {
                Block block = Block.blocksList[target.getBlockID(this.worldObj)];
                if (MachineMiningEvent.doMachineMiningCheck(this.worldObj, target, this))
                {
                    worldObj.setBlockToAir(target.intX(), target.intY(), target.intZ());
                    this.consumePower(1.5f, true);

                    List<ItemStack> items = MachineMiningEvent.getItemsMined(this.worldObj, target, this);
                    if (items != null)
                    {
                        for (ItemStack stack : items)
                        {
                            this.dropItems(stack);
                        }
                    }
                    target.translate(Vector3.DOWN());
                }
                else if (block == null || block.isAirBlock(this.worldObj, target.intX(), target.intY(), target.intZ()))
                {
                    target.translate(Vector3.DOWN());
                }
            }
        }
    }

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
