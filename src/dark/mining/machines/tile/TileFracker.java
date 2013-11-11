package dark.mining.machines.tile;

import net.minecraft.block.Block;
import universalelectricity.core.vector.Vector3;
import dark.core.common.blocks.BlockGasOre;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.MMRecipeLoader;
import dark.mining.MechanizedMining;

/** @author Archadia */
public class TileFracker extends TileEntityEnergyMachine
{

    private Vector3 target, target2;
    
    public static final float STAGE_1_USAGE = 0.5f;
    public static final float STAGE_2_USAGE = 0.5f;
    public static final float STAGE_3_USAGE = 2.0f;
    public static final float STAGE_4_USAGE = 4.0f;

    public TileFracker()
    {
        super(0, 5);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
        	if(this.ticks % 20 == 0) {
        		//clearArea();
    			collect();
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
		}
    }

    public void collect() {
    	if(target2 == null) {
    		target2 = new Vector3(xCoord, yCoord, zCoord);
    	}
    	if(target2.intY() > 0) {
			if(this.getEnergyStored() >= STAGE_2_USAGE) {
				target2.translate(Vector3.DOWN());
	
	   			int blockID = target2.getBlockID(this.worldObj);
	   			Block block = Block.blocksList[blockID];
	   			if(block == null) {
	   				worldObj.setBlock(target2.intX(), target2.intY(), target2.intZ(), MMRecipeLoader.frackingPipe.blockID);
	   				this.consumePower(STAGE_2_USAGE, true);
	   			}
			}
    	}
    }
}