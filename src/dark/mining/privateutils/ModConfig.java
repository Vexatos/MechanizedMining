package dark.mining.privateutils;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Loader;

/** @author Archadia */
public class ModConfig
{

    private static HashMap<String, Configuration> configs = new HashMap<String, Configuration>();

    public static Configuration addConfig(String name)
    {
        Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + "/Dark/MechanizedMining/" + name + ".cfg"));
        configs.put(name, config);
        return configs.get(name);
    }

    public static Configuration getConfig(String name)
    {
        return configs.get(name);
    }
}
