package dark.mining.common;

import java.io.File;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Loader;
import dark.mining.common.block.BlockMechanized;
import dark.mining.common.machines.scanner.BlockScanner;
import dark.mining.common.machines.scanner.TileEntityScanner;


/**
 * @author Archadia
 *
 */

public class MMConfig {

	public static Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + "/Dark/MechanizedMining.cfg"));
	
	public static MMObjectLoader objLoader = new MMObjectLoader();
	
	public void addConfigBlocks() {
		config.load();
		
		objLoader.addMMObject("mechanizedBlock", BlockMechanized.class, null);
		objLoader.addMMObject("scanner", BlockScanner.class, TileEntityScanner.class);

		config.save();
	}

}
