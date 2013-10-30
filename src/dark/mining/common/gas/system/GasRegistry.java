package dark.mining.common.gas.system;

import java.util.ArrayList;
import java.util.HashSet;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import dark.mining.common.gas.system.core.Gas;
import dark.mining.common.gas.system.core.GasStack;

/**
 * @author Archadia
 *
 */
public class GasRegistry {

	static int currentID;
	
	static HashSet<String> usedNames = new HashSet<String>();
	static ArrayList<Gas> gasses = new ArrayList<Gas>();
	static BiMap<String, Integer> gasIDs = HashBiMap.create();

	static void initGasIDs(BiMap<String, Integer> newGasIds)
    {
		currentID = newGasIds.size();
		gasIDs.clear();
        gasIDs.putAll(newGasIds);
    }

    public static boolean registerGas(Gas gas) {
    	if(usedNames.contains(gas.getName())) {
    		return false;
    	}
        if (gasIDs.containsKey(gas.getName()))
        {
            return false;
        }
        gasses.add(gas);
        gasIDs.put(gas.getName(), ++currentID);
        usedNames.add(gas.getName());
        return true;
    }

    public static boolean isGasRegistered(Gas gas)
    {
        return gasIDs.containsKey(gas.getName());
    }

    public static boolean isGasRegistered(String GasName)
    {
        return gasIDs.containsKey(GasName);
    }

    public static Gas getGas(String gasName)
    {
    	return getGas(getGasID(gasName));
    }

    public static Gas getGas(int gasID)
    {
    	return gasses.get(gasID);
    }

    public static String getGasName(int gasID)
    {
        return gasIDs.inverse().get(gasID);
    }

    public static String getGasName(GasStack stack)
    {
        return getGasName(stack.gasID);
    }

    public static int getGasID(String gasName)
    {
        return gasIDs.get(gasName);
    }

    public static GasStack getGasStack(String gasName, int amount)
    {
        if (!gasIDs.containsKey(gasName))
        {
            return null;
        }
        return new GasStack(getGasID(gasName), amount);
    }
}
