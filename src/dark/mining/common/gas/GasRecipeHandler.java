package dark.mining.common.gas;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class GasRecipeHandler {
	private HashMap<Integer, Gas> recipes = new HashMap<Integer, Gas>();
	
	public Gas getResult(int input) {
		Gas gas = this.recipes.get(input);
		if (gas == null) {
			return null;
		}
		return this.recipes.get(input);
	}
	
	public void addGasRecipe(int input, Gas output) {
		recipes.put(input, output);
	}
}
