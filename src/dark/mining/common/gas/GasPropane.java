package dark.mining.common.gas;

import gas.system.Gas;
import gas.system.GasRegistry;


/**
 * @author Archadia
 *
 */
public class GasPropane extends Gas {

	public GasPropane(int id, String type) {
		super(id, type);
		setDensity(493000f);
		GasRegistry.registerGas(this);
	}
}
