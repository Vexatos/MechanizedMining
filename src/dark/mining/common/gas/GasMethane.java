package dark.mining.common.gas;

import gas.system.Gas;
import gas.system.GasRegistry;


/**
 * @author Archadia
 *
 */
public class GasMethane extends Gas {

	public GasMethane(int id, String type) {
		super(id, type);
		setDensity(660);
		GasRegistry.registerGas(this);
	}

}
