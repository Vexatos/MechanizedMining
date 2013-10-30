package dark.mining.common.gas.machines.interfaces;

import dark.mining.common.gas.system.core.Gas;
import dark.mining.common.gas.system.core.GasDisplay;
import dark.mining.common.gas.system.core.GasStack;

/**
 * @author Archadia
 *
 */
public interface IGasTank {

	GasStack getStack();
	
    Gas getGas();
    
    int getGasAmount();
    
    void setGasStored(Gas gas, int amt);
    
    GasDisplay getDisplayInfo();
}
