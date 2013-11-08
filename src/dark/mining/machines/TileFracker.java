package dark.mining.machines;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import dark.core.common.DarkMain;
import dark.core.interfaces.IMultiBlock;
import dark.core.prefab.gas.GasTank;
import dark.core.prefab.machine.BlockMulti;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.block.BlockNaturalGas;
import dark.mining.privateutils.WorldHelper;

/** @author Archadia */
public class TileFracker extends TileEntityEnergyMachine implements IMultiBlock
{

    GasTank tank = new GasTank(5000);

    private int target;
    private boolean isBeingRotated = false;

    public TileFracker()
    {
        super(0.5f);
        this.rotateByMetaGroup = true;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (getEnergyStored() >= 1000)
        {
            target = yCoord;
            target--;

            Block blockUsing = WorldHelper.getBlock(worldObj, xCoord, target, zCoord);

            if (!(blockUsing instanceof BlockNaturalGas))
            {
                if (!(blockUsing == Block.bedrock))
                {
                    worldObj.setBlock(xCoord, target, zCoord, 0);
                }
            }
            else
            {
                //etc etc etc
            }
        }
    }

    @Override
    public boolean onActivated(EntityPlayer entityPlayer)
    {
        // TODO Open gui when called from another block
        return false;
    }

    @Override
    public void onCreate(Vector3 placedPosition)
    {
        if (DarkMain.blockMulti instanceof BlockMulti)
        {
            DarkMain.blockMulti.makeFakeBlock(this.worldObj, Vector3.translate(placedPosition, new Vector3(0, 1, 0)), placedPosition);
        }
    }

    @Override
    public void onDestroy(TileEntity callingBlock)
    {
        this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, 0, 0, 3);
        this.worldObj.setBlock(this.xCoord, this.yCoord + 1, this.zCoord, 0, 0, 3);
    }

    @Override
    public void setDirection(ForgeDirection direction)
    {
        super.setDirection(direction);
        //TODO rotate the multi-block if it is not the same on all sides.
    }
}
