package dark.mining.common.mech.fracking;

import net.minecraft.tileentity.TileEntity;
import dark.mining.common.gas.machines.interfaces.ITileGasTank;
import dark.mining.common.gas.machines.storage.tank.GasTank;
import dark.mining.common.gas.system.core.Gas;

/**
 * @author Archadia
 *
 */
public class TileFracker extends TileEntity implements ITileGasTank {

	GasTank tank = new GasTank(5000);

	@Override
	public void setGasStored(Gas gas, int amt) {
		tank.setGasStored(gas, amt);
	}
	
	public void updateEntity() {
		super.updateEntity();
		
		
	}
}
