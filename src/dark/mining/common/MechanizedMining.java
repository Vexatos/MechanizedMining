package dark.mining.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import dark.core.prefab.ModPrefab;
import dark.core.registration.ModObjectRegistry;
import dark.mining.common.machines.groundradar.BlockGroundRadar;
import dark.mining.common.machines.groundradar.TileEntityGroundRadar;
import dark.mining.common.machines.scanner.BlockScanner;
import dark.mining.common.machines.scanner.TileEntityScanner;

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

    public static Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + "/Dark/MechanizedMining.cfg"));

    @EventHandler
    public void Init(FMLInitializationEvent e)
    {

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
    public void registerObjects()
    {
        config.load();
	        addMMObject("scanner", BlockScanner.class, TileEntityScanner.class);
	        addMMObject("groundradar", BlockGroundRadar.class, TileEntityGroundRadar.class);
        config.save();
    }

    @Override
    public void loadModMeta()
    {

    }

    /** Method to add a MM block, uses CoreMachines' loader mixed with Archadia's personal loader. */
    public void addMMObject(String name, Class<? extends Block> blockClass, Class<? extends TileEntity> tileClass)
    {
        Block block = ModObjectRegistry.createNewBlock(name, MechanizedMining.MOD_ID, blockClass, true);
        if(tileClass != null) {
        	String tileName = "tileEntity" + name.toUpperCase();
        	GameRegistry.registerTileEntity(tileClass, tileName);
        }
    }
}
