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

    private Vector3 target;

    public TileFracker()
    {
        super(0, 10);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
		if (target == null) {
			target = new Vector3(xCoord, yCoord, zCoord);
		}
        if (!worldObj.isRemote) {
        	if(this.ticks % 20 == 0) {
        		if(target.intY() > 0) {
        			if(this.getEnergyStored() >= 4) {
	        			 target.translate(Vector3.DOWN());
	
	        			 int blockID = target.getBlockID(this.worldObj);
	        			 Block block = Block.blocksList[blockID];
	        			 if (block != null && !block.isAirBlock(this.worldObj, target.intX(), target.intY(), target.intZ()) && block.getBlockHardness(this.worldObj, target.intX(), target.intY(), target.intZ()) >= 0) {
	        				 worldObj.setBlockToAir(target.intX(), target.intY(), target.intZ());
	        				 this.consumePower(.5f, true);
	        			 }
        			}
        		}
        	}
    	}
    }
}