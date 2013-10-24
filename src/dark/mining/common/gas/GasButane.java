package dark.mining.common.gas;

import gas.system.Gas;
import gas.system.GasRegistry;


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
