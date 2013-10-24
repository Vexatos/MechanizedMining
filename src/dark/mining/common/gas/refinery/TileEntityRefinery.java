package dark.mining.common.gas.refinery;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import dark.mining.common.gas.GasRecipeHandler;

/**
 * @author Archadia
 *
 */
public class TileEntityRefinery extends TileEntity {

	ItemStack[] inputInventory = new ItemStack[1];
	FluidStack[] outputInventory = new FluidStack[1];
	
	public void updateEntity() {
		super.updateEntity();
	}
	
	public boolean canProcess() {
		if(inputInventory[0] == null) {
			return false;
		}
		return true;
    }
	
	public void processItems() {
		
	}
}
