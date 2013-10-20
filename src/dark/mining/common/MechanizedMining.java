package dark.mining.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import dark.core.prefab.ModPrefab;
import dark.mining.common.block.BlockRubble;
import dark.mining.common.mech.block.scanner.BlockScanner;
import dark.mining.common.mech.item.ItemHandDrill;
import dark.mining.common.privateutils.ModConfig;
import dark.mining.common.privateutils.ModObjectHandler;

/** Main Mod class for MechanizedMining.
 *
 * @author Archadia */
@Mod(modid = MechanizedMining.MOD_ID, name = MechanizedMining.MOD_NAME, version = MechanizedMining.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class MechanizedMining extends ModPrefab
{

    @Instance(MechanizedMining.MOD_ID)
    public static MechanizedMining instance;

    @SidedProxy(clientSide = "dark.mining.client.ClientProxy", serverSide = "dark.mining.common.CommonProxy")
    public static CommonProxy proxy;

    public static final String MOD_ID = "MechanizedMining";
    public static final String MOD_NAME = "Mechanized-Mining";

    /** Version #s are handled by the jenkins server */
    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVIS_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVIS_VERSION + "." + BUILD_VERSION;

    @EventHandler
    public void Init(FMLInitializationEvent es)
    {
    	ModConfig.addConfig("Objects");
    	
    	ModConfig.getConfig("Objects").load();
    	ModObjectHandler.addBlockObj("scanner", BlockScanner.class, true);
    	ModObjectHandler.addBlockObj("rubble", BlockRubble.class, true);

    	ModObjectHandler.addItemObj("handDrill", ItemHandDrill.class, true);
    	ModConfig.getConfig("Objects").save();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }

    @Override
    public String getDomain()
    {
        return MechanizedMining.MOD_ID;
    }

    @Override
    public void registerObjects() {

    }

    @Override
    public void loadModMeta()
    {

    }
}
