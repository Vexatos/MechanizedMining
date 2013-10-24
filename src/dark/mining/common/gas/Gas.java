package dark.mining.common.gas;

import net.minecraftforge.fluids.Fluid;

/**
 * @author Archadia
 *
 */
public class Gas extends Fluid {

	private GasTypes gas;
	
	public Gas(String fluidName) {
		super(fluidName);
		setGaseous(true);
	}
}
