package dark.mining.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import dark.mining.common.proxy.CommonProxy;

/**
 * Main Mod class for MechanizedMining.
 * @author Archadia
 */
@Mod(modid = MechanizedMining.MOD_ID, name = MechanizedMining.MOD_NAME, version = MechanizedMining.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class MechanizedMining {

	@Instance(MechanizedMining.MOD_ID)
	public static MechanizedMining instance;
	
	@SidedProxy(clientSide = "dark.mining.common.proxy.ClientProxy", serverSide = "dark.mining.common.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static final String MOD_ID = "MechanizedMining";
	public static final String MOD_NAME = "Mechanized-Mining";
	public static final String MOD_VERSION = "0.0.1";
				
	MMConfig config = new MMConfig();
	
	@EventHandler
	public void Init(FMLInitializationEvent e) {
		config.addConfigBlocks();
	}
}
