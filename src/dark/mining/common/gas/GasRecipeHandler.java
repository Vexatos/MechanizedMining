package dark.mining.common.gas;

import java.util.HashMap;

import net.minecraftforge.fluids.Fluid;

/**
 * @author Archadia
 *
 */
public class GasRecipeHandler {
	private HashMap<Integer, Fluid> recipes = new HashMap<Integer, Fluid>();
	
	public Fluid getResult(int input) {
		Fluid gas = this.recipes.get(input);
		if (gas == null) {
			return null;
		}
		return this.recipes.get(input);
	}
	
	public void addGasRecipe(int input, Fluid output) {
		recipes.put(input, output);
	}
}
