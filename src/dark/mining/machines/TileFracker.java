package dark.mining.machines;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import dark.core.common.DarkMain;
import dark.core.common.blocks.BlockGasOre;
import dark.core.interfaces.IMultiBlock;
import dark.core.prefab.fluids.GasTank;
import dark.core.prefab.machine.BlockMulti;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.privateutils.WorldHelper;

/** @author Archadia */
public class TileFracker extends TileEntityEnergyMachine
{

    private int target = yCoord;

    public TileFracker()
    {
        super(0);
    }

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote) {
        	System.out.println(getEnergyStored());
	        if (getEnergyStored() >= 1)
	        {
	            target--;
	            System.out.println(xCoord + ", " + target + ", " + zCoord);
	            worldObj.setBlock(xCoord, target, zCoord, 0);
	            this.setEnergyStored(this.getEnergyStored() - 1);
	        }
        }
    }
    
    @Override
    public float getMaxEnergyStored()
    {
        return 60;
    }
    
    @Override
    public float getRequest(ForgeDirection direction)
    {
        return Math.max(this.getMaxEnergyStored() - this.getEnergyStored(), 0);
    }

}
