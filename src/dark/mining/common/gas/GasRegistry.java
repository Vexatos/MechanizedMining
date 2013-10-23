package dark.mining.common.gas;

import java.util.HashMap;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author Archadia
 *
 */
public class GasRegistry {

	static int currentID = 0;
	
	static HashMap<String, Fluid> storedGas = new HashMap<String, Fluid>();
    static BiMap<String, Integer> gasIDs = HashBiMap.create();
    
    public static boolean registerGas(Fluid gas) {
    	if(gasIDs.containsKey(gas.getName())) {
    		return false;
    	}
    	storedGas.put(gas.getName(), gas);
    	gasIDs.put(gas.getName(), currentID++);
    	FluidRegistry.registerFluid(gas);
    	return true;
    }

    public Fluid getGas(String name) {
    	return storedGas.get(name);
    }
    
    public String getGasName(int id) {
    	return gasIDs.inverse().get(id);
    }
    
    public int getGasID(String name) {
    	return gasIDs.get(name);
    }
}
