package dark.mining.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Loader;
import dark.mining.common.block.BlockMechanized;
import dark.mining.common.block.BlockScanner;


/**
 * @author Archadia
 *
 */
public class MMConfig {

	public static Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + "/MechanizedMining.cfg"));
	
	public static MMObjectLoader objLoader = new MMObjectLoader();
	
	public void addConfigBlocks() {
		config.load();
		
		objLoader.addMMObject("mechanizedBlock", MechanizedMining.MOD_ID, BlockMechanized.class, true);
		objLoader.addMMObject("scanner", MechanizedMining.MOD_ID, BlockScanner.class, true);

		config.save();
	}

}
