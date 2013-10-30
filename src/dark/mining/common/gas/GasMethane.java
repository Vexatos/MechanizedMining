package dark.mining.common.gas;

import dark.mining.common.gas.system.GasRegistry;
import dark.mining.common.gas.system.core.Gas;


/**
 * @author Archadia
 *
 */
public class GasMethane extends Gas {

	public GasMethane(int id, String type, float density) {
		super(id, density, type);
		GasRegistry.registerGas(this);
	}

}
