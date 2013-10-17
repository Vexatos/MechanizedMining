package dark.mining.common.privateutils;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Loader;

/**
 * @author Archadia
 *
 */
public class ModConfig {
	
	
	private static HashMap<String, Configuration> configs = new HashMap<String, Configuration>();
    
    public static void addConfig(String name) {
    	Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + "/Dark/MechanizedMining/" + name + ".cfg"));
    	configs.put(name, config);
    }
    
    public static Configuration getConfig(String name) {
    	return configs.get(name);
    }
}
