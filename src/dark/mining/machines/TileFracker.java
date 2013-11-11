package dark.mining.machines;

import net.minecraft.block.Block;
import universalelectricity.core.vector.Vector3;
import dark.core.common.blocks.BlockGasOre;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.MechanizedMining;

/** @author Archadia */
public class TileFracker extends TileEntityEnergyMachine
{

    private Vector3 target;
    private boolean flag = false;
    
    public static final float STAGE_1_USAGE = 0.5f;
    public static final float STAGE_2_USAGE = 0.5f;
    public static final float STAGE_3_USAGE = 2.0f;
    public static final float STAGE_4_USAGE = 4.0f;

    public TileFracker()
    {
        super(0, 10);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
        	if(this.ticks % 20 == 0) {
        		if(!flag) clearArea();
    			if(flag) collect();
        	}
    	}
    }
    
    /** Returns if its finished its job */
    public void clearArea() {
		if (target == null) {
			target = new Vector3(xCoord, yCoord, zCoord);
		}
		if(target.intY() > 0) {
			if(this.getEnergyStored() >= STAGE_1_USAGE) {
    			 target.translate(Vector3.DOWN());

    			 int blockID = target.getBlockID(this.worldObj);
    			 Block block = Block.blocksList[blockID];
    			 if (block != null && !block.isAirBlock(this.worldObj, target.intX(), target.intY(), target.intZ()) && block.getBlockHardness(this.worldObj, target.intX(), target.intY(), target.intZ()) >= 0) {
    				 worldObj.setBlockToAir(target.intX(), target.intY(), target.intZ());
    				 this.consumePower(STAGE_1_USAGE, true);
    			 }
			}
			flag = false;
		} else {
			flag = true;
			target = null;
		}
    }

    public void collect() {
    	if(target == null) {
    		target = new Vector3(xCoord, yCoord, zCoord);
    	}
    	if(target.intY() > 0) {
			if(this.getEnergyStored() >= STAGE_4_USAGE) {
				target.translate(Vector3.DOWN());
	
	   			int blockID = target.getBlockID(this.worldObj);
	   			Block block = Block.blocksList[blockID];
	   			if (block != null && !block.isAirBlock(this.worldObj, target.intX(), target.intY(), target.intZ()) && block.getBlockHardness(this.worldObj, target.intX(), target.intY(), target.intZ()) >= 0) {
	   				if(block instanceof BlockGasOre) {

		   				worldObj.setBlockToAir(target.intX(), target.intY(), target.intZ());
		   				this.consumePower(STAGE_4_USAGE, true);
		   				//add gas to container
	   				}
	   				worldObj.setBlock(target.intX(), target.intY(), target.intZ(), MechanizedMining.frackingPipe.blockID);
	   				this.consumePower(STAGE_2_USAGE, true);
	   			}
			}
    	} else {
    		target = null;
    	}
    }
}