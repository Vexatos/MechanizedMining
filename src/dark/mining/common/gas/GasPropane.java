package dark.mining.common.gas;

import gas.system.GasRegistry;
import gas.system.core.Gas;


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
