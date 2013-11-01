package dark.mining.common.gas;

import dark.mining.common.gas.system.GasRegistry;
import dark.mining.common.gas.system.core.Gas;

/**
 * @author Archadia
 *
 */
public class GasNatural extends Gas {

	public GasNatural(int id, String type, float density) {
		super(id, density, type);
		GasRegistry.registerGas(this);
	}
}
