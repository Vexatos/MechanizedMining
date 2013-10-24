package dark.mining.common.gas.refinery;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.TileFluidHandler;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import dark.mining.common.MechanizedMining;
import dark.mining.common.gas.GasTypes;

/**
 * @author Archadia
 *
 */
public class TileEntityRefinery extends TileFluidHandler {

	ItemStack[] inputInventory = new ItemStack[1];
	
	public void updateEntity() {
		super.updateEntity();
	}
	
	public boolean canProcess() {
		if(inputInventory[0] == null) {
			return false;
		}
		if(inputInventory[0].itemID == MechanizedMining.blockNaturalGas.blockID) {
			return true;
		}
		return false;
    }
	
	public void processItems() {
		if(canProcess()) {
			System.out.println(chooseOutput());
		}
	}
	
	public BiMap chooseOutput() {
		return null;
	}
}
