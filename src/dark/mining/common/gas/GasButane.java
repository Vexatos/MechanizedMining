package dark.mining.common.gas;

import gas.system.GasRegistry;
import gas.system.core.Gas;


/**
 * @author Archadia
 *
 */
public class GasButane extends Gas {

	public GasButane(int id, String type) {
		super(id, type);
		setDensity(0.00248f);
		GasRegistry.registerGas(this);
	}
}
