package dark.mining.common.gas;

import java.util.HashMap;

import net.minecraftforge.fluids.Fluid;

/**
 * @author Archadia
 *
 */
public class GasRecipeHandler {
	private static HashMap<Integer, Fluid> recipes = new HashMap<Integer, Fluid>();
	
	public static Fluid getResult(int input) {
		Fluid gas = recipes.get(input);
		if (gas == null) {
			return null;
		}
		return recipes.get(input);
	}
	
	public static void addGasRecipe(int input, Fluid output) {
		recipes.put(input, output);
	}
}
