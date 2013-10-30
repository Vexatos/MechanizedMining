package dark.mining.common.mech.gastank;

import net.minecraft.tileentity.TileEntity;
import dark.mining.common.gas.machines.interfaces.ITileGasTank;
import dark.mining.common.gas.machines.storage.tank.GasTank;
import dark.mining.common.gas.system.core.Gas;


/**
 * @author Archadia
 *
 */
public class TileEntityGasTank extends TileEntity implements ITileGasTank {

	GasTank tank = new GasTank(5000);
	
	public TileEntityGasTank() {

	}

    public void updateEntity() {
    	super.updateEntity();
    }

	@Override
	public void setGasStored(Gas gas, int amt) {
		tank.setGasStored(gas, amt);
	}
}
