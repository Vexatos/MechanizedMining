package dark.mining;

import java.util.Arrays;

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
import dark.core.ModObjectRegistry;
import dark.core.prefab.ModPrefab;
import dark.machines.DarkMain;
import dark.mining.block.BlockFrackingPipe;
import dark.mining.block.BlockRubble;
import dark.mining.item.tool.ItemHandDrill;
import dark.mining.item.tool.ItemInstaHole;
import dark.mining.item.tool.ItemMiningLaser;
import dark.mining.machines.block.BlockApertureExcavator;
import dark.mining.machines.block.BlockFracker;
import dark.mining.machines.block.BlockLaserDrill;
import dark.mining.machines.block.BlockMiningLaser;
import dark.mining.machines.block.BlockScanner;
import dark.mining.privateutils.ModConfig;

/** Main Mod class for MechanizedMining.
 *
 * @author Archadia */
@Mod(modid = MechanizedMining.MOD_ID, name = MechanizedMining.MOD_NAME, version = MechanizedMining.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class MechanizedMining extends ModPrefab
{

    @Instance(MechanizedMining.MOD_ID)
    public static MechanizedMining instance;

    @SidedProxy(clientSide = "dark.mining.client.ClientProxy", serverSide = "dark.mining.CommonProxy")
    public static CommonProxy proxy;

    public static final String MOD_ID = "MechanizedMining";
    public static final String MOD_NAME = "Mechanized-Mining";

    /** Version #s are handled by the jenkins server */
    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVIS_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVIS_VERSION + "." + BUILD_VERSION;

    @Metadata(MOD_ID)
    public static ModMetadata meta;

    Configuration confObj;

    static MMRecipeLoader recipeLoader = new MMRecipeLoader();

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //Register event handlers here
        super.preInit(event);
        proxy.preInit();
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
        instance = this;
        confObj = ModConfig.addConfig("Objects");
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        proxy.init();
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
        proxy.postInit();
    }

    @Override
    public String getDomain()
    {
        return MechanizedMining.MOD_ID.toLowerCase();
    }
    
    @Override
    public void registerObjects()
    {
        confObj.load();
        //Blocks  
        
        MMRecipeLoader.machineFracker = ModObjectRegistry.createNewBlock("fracker", MOD_ID, BlockFracker.class, true);
        if (MMRecipeLoader.machineFracker != null)
        {
            MMRecipeLoader.frackingPipe = ModObjectRegistry.createNewBlock("frackingPipe", MOD_ID, BlockFrackingPipe.class, false);
        }

        MMRecipeLoader.machineScanner = ModObjectRegistry.createNewBlock("scanner", MOD_ID, BlockScanner.class, true);
        MMRecipeLoader.rubble = ModObjectRegistry.createNewBlock("rubble", MOD_ID, BlockRubble.class, true);
        MMRecipeLoader.machineApertureExc = ModObjectRegistry.createNewBlock("apertureExcavator", MOD_ID, BlockApertureExcavator.class, true);
        
        MMRecipeLoader.laserSentry = ModObjectRegistry.createNewBlock("laserSentry", MOD_ID, BlockMiningLaser.class, true);
        MMRecipeLoader.laserDrill = ModObjectRegistry.createNewBlock("laserDrill", MOD_ID, BlockLaserDrill.class, true);

        //Items
        MMRecipeLoader.toolDrill = ModObjectRegistry.createNewItem("handDrill", MOD_ID, ItemHandDrill.class, true);
        MMRecipeLoader.toolHoleCreator = ModObjectRegistry.createNewItem("toolHoleCreator", MOD_ID, ItemInstaHole.class, true);
        MMRecipeLoader.toolMiningLaser = ModObjectRegistry.createNewItem("toolMiningLaser", MOD_ID, ItemMiningLaser.class, true);

        //Gas
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
    public void loadRecipes()
    {
        recipeLoader.loadRecipes();
    }
}
