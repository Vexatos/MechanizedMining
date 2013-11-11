package dark.mining.machines.tile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import dark.core.helpers.InvInteractionHelper;
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
            this.invExtractionHelper = new InvInteractionHelper(this.worldObj, new Vector3(this), null, false);
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
            target = new Vector3(xCoord, yCoord, zCoord);
        }
        if (target.intY() > 0)
        {
            if (this.getEnergyStored() >= 1.5f)
            {
                target.translate(Vector3.DOWN());

                int blockID = target.getBlockID(this.worldObj);
                Block block = Block.blocksList[blockID];
                if (block != null && !block.isAirBlock(this.worldObj, target.intX(), target.intY(), target.intZ()) && block.getBlockHardness(this.worldObj, target.intX(), target.intY(), target.intZ()) >= 0)
                {
                    worldObj.setBlockToAir(target.intX(), target.intY(), target.intZ());
                    this.consumePower(1.5f, true);
                    ItemStack drop = block.getBlockDropped(worldObj, target.intX(), target.intY(), target.intZ(), worldObj.getBlockMetadata(target.intX(), target.intY(), target.intZ()), 0).get(0);

                    if (block != Block.waterMoving || block != Block.waterMoving || block != Block.lavaMoving || block != block.lavaStill)
                    {
                        dropItems(drop);
                    }
                }
            }
        }
    }

    private void dropItems(ItemStack item)
    {
        if(item != null)
        {
            item = this.invHelper().storeItem(item, ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
            if(item != null)
            {
                this.invHelper().throwItem(new Vector3(this).modifyPositionFromSide(ForgeDirection.UP), item);
                item = null;
            }
        }
    }
}
