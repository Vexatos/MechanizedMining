package dark.mining.common.mech.fracking;

import net.minecraft.block.Block;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.common.block.BlockNaturalGas;
import dark.mining.common.gas.machines.interfaces.ITileGasTank;
import dark.mining.common.gas.machines.storage.tank.GasTank;
import dark.mining.common.gas.system.core.Gas;
import dark.mining.common.privateutils.WorldHelper;

/**
 * @author Archadia
 *
 */
public class TileFracker extends TileEntityEnergyMachine implements ITileGasTank {

	GasTank tank = new GasTank(5000);

	private int target;
	
    public TileFracker() {
        super(0.5f);
    }
	
	@Override
	public void setGasStored(Gas gas, int amt) {
		tank.setGasStored(gas, amt);
	}
	
	public void updateEntity() {
		super.updateEntity();
		
		if(getEnergyStored() >= 1000) {
			target = yCoord;
			target--;
			
			Block blockUsing = WorldHelper.getBlock(worldObj, xCoord, target, zCoord);
			
			if(!(blockUsing instanceof BlockNaturalGas)) {
				if(!(blockUsing == Block.bedrock)) {
					worldObj.setBlock(xCoord, target, zCoord, 0);
				}
			} else {
				//etc etc etc
			}
		}
	}
}
