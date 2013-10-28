package dark.mining.common.mech.gastank;

import gas.machines.TileGasSorter;

/**
 * @author Archadia
 *
 */
public class TileEntityGasTank extends TileGasSorter {

	public TileEntityGasTank() {
		super(5000);
	}

    public void updateEntity() {
    	super.updateEntity();
    	
    	if(!worldObj.isRemote) {
    		if(worldObj.getWorldTime()%100==0) {
    			System.out.println("Gas Stored: " + tank.getGasAmount() + ", " + tank.getGas());
    		}
    	}
    }
}
