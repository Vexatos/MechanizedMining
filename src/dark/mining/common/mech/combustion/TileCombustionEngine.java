package dark.mining.common.mech.combustion;

import net.minecraft.tileentity.TileEntity;
import dark.mining.common.gas.machines.interfaces.ITileGasTank;
import dark.mining.common.gas.machines.storage.tank.GasTank;
import dark.mining.common.gas.system.core.Gas;

/**
 * @author Archadia
 *
 */
public class TileCombustionEngine extends TileEntity implements ITileGasTank {

	GasTank tank = new GasTank(1000);
	
	public TileCombustionEngine() {

	}

    public void updateEntity() {
    	super.updateEntity();
    }

	@Override
	public void setGasStored(Gas gas, int amt) {
		tank.setGasStored(gas, amt);
	}
}
