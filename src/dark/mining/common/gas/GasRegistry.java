package dark.mining.common.gas;

import java.util.HashMap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author Archadia
 *
 */
public class GasRegistry {

	static int idLimit = 0;
	
	static HashMap<String, Gas> storedGas = new HashMap<String, Gas>();
    static BiMap<String, Integer> gasIDs = HashBiMap.create();
    
    public static boolean registerGas(Gas gas) {
    	if(gasIDs.containsKey(gas.getName())) {
    		return false;
    	}
    	storedGas.put(gas.getName(), gas);
    	gasIDs.put(gas.getName(), idLimit++);
    	return true;
    }
    
}
