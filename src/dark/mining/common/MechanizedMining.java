package dark.mining.common;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import dark.core.common.DarkMain;
import dark.core.prefab.ModPrefab;
import dark.core.registration.ModObjectRegistry;
import dark.mining.common.block.BlockRubble;
import dark.mining.common.gas.GasButane;
import dark.mining.common.gas.GasMethane;
import dark.mining.common.gas.GasPropane;
import dark.mining.common.gas.system.core.Gas;
import dark.mining.common.item.tool.ItemHandDrill;
import dark.mining.common.item.tool.ItemInstaHole;
import dark.mining.common.item.tool.ItemMiningLaser;
import dark.mining.common.mech.gastank.BlockGasTank;
import dark.mining.common.mech.scanner.BlockScanner;
import dark.mining.common.privateutils.ModConfig;

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

    public static Block machineScanner, rubble, gasTank;
    public static Item toolDrill, toolHoleCreator, toolMiningLaser;

    @Metadata(MOD_ID)
    public static ModMetadata meta;

    public static Gas methane, butane, propane;
    
    Configuration confObj;
    
    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //Register event handlers here
        super.preInit(event);
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
        instance = this;
        confObj = ModConfig.addConfig("Objects");        
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
        //register recipes in the recipe call
    }

    @Override
    public String getDomain()
    {
        return MechanizedMining.MOD_ID;
    }

    @Override
    public void registerObjects()
    {
    	confObj.load();
        //Blocks
    	gasTank = ModObjectRegistry.createNewBlock("gasTank", MOD_ID, BlockGasTank.class, true);
        machineScanner = ModObjectRegistry.createNewBlock("scanner", MOD_ID, BlockScanner.class, true);
        rubble = ModObjectRegistry.createNewBlock("rubble", MOD_ID, BlockRubble.class, true);
        //Items
        toolDrill = ModObjectRegistry.createNewItem("handDrill", MOD_ID, ItemHandDrill.class, true);
        toolHoleCreator = ModObjectRegistry.createNewItem("toolHoleCreator", MOD_ID, ItemInstaHole.class, true);
        toolMiningLaser = ModObjectRegistry.createNewItem("toolMiningLaser", MOD_ID, ItemMiningLaser.class, true);
        //Gas
		methane = new GasMethane(0, "methane", 660f);
		butane = new GasButane(1, "butane", 0.00248f);
		propane = new GasPropane(2, "propane", 493000f);
        confObj.save();
    }

    @Override
    public void loadModMeta()
    {
        /* MCMOD.INFO FILE BUILDER? */
        meta.modId = MOD_ID;
        meta.name = MOD_NAME;
        meta.description = "Mining done on a scale that shacks the earth to rubble.";
        meta.url = "http://universalelectricity.com/coremachine";

        meta.logoFile = ModPrefab.TEXTURE_DIRECTORY + "MM_Banner.png";
        meta.version = DarkMain.VERSION;
        meta.authorList = Arrays.asList(new String[] { "Archadia", "DarkGuardsman" });
        meta.credits = "Please see the website.";
        meta.autogenerated = false;
    }

    @Override
    public void loadRecipes() {
        if (machineScanner instanceof BlockScanner){
            //load recipe here and make sure to do the same per item since they can be disabled by the user
        }
    }
}
