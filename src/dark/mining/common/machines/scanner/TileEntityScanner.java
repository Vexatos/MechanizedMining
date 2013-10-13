package dark.mining.common.machines.scanner;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.common.privateutils.TripleContainer;

/** @author Archadia */
public class TileEntityScanner extends TileEntityEnergyMachine {

    private ArrayList<Integer> validTarget = new ArrayList<Integer>();
    private TripleContainer<Integer, Integer, Integer> usedTarget = new TripleContainer<Integer, Integer, Integer>();

    public boolean enabled = true;

    Vector3[] vec3 = new Vector3[15];
    
    public TileEntityScanner() {
        super(0.5f); //500W
        
    }

    @Override
    public void updateEntity() {
    	super.updateEntity();
    	
        if (!worldObj.isRemote) {
            for (byte i = 0; i < 5 && this.canFunction(); i++) {
            	System.out.println("Hello come again!");
                scanArea();
            }
        }
    }

    @Override
    public boolean canFunction() {
        return super.canFunction() && this.enabled;
    }

    /** Scans the area for valid blocks */
    protected void scanArea() {    	
    	int firstY = this.yCoord - 1;
    	int firstZ = this.zCoord - 8;
    	
    	float curZ = 0;
    	
    	for(int i = 0; i < vec3.length; i++) {
	    		vec3[i] = new Vector3((this.xCoord - 8) + i, firstY, firstZ);
	    		curZ = vec3[i].floatZ();
	    	if(curZ < firstZ + 15) {
	    		if(isTargetValid(vec3[i].getBlockID(worldObj))) {
	    			printResult(vec3[i].getBlockID(worldObj));
	    		}
	    		vec3[i].translate(new Vector3(0, 0, 1));
    		} else {
    			vec3[i].translate(new Vector3(0, 1, 0));
    			return;
    		}
    	}
    	
    }

    protected boolean isTargetValid(int id) {
    	if(id == Block.stone.blockID) {
    		return true;
    	}
        return false;
    }

    protected void printResult(int id) {
        System.out.println("Found " + id);
    }

    @Override
    public boolean canConnect(ForgeDirection dir) {
        return true;
    }

}