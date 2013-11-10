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


	private Vector3 target = new Vector3(xCoord, yCoord, zCoord);
	
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
	        	if(worldObj.getWorldTime()%20==0) {
	        		target.translate(Vector3.DOWN(), 1);
	        		System.out.println(worldObj.getBlockId(target.intX(), target.intY(), target.intZ()));
		            worldObj.setBlock(target.intX(), target.intY(), target.intZ(), 0);
		            this.setEnergyStored(this.getEnergyStored() - 2);
	        	}
	        }
        }
    }
    
    @Override
    public float getMaxEnergyStored()
    {
        return 10;
    }
    
    @Override
    public float getRequest(ForgeDirection direction)
    {
        return Math.max(this.getMaxEnergyStored() - this.getEnergyStored(), 0);
    }

}
