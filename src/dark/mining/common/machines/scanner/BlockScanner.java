package dark.mining.common.machines.scanner;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dark.core.prefab.ModPrefab;
import dark.mining.common.MMConfig;
import dark.mining.common.block.BlockMM;

/**
 * @author Archadia
 *
 */
public class BlockScanner extends BlockMM {

	public BlockScanner() {
		super(MMConfig.config.getBlock("scanner", ModPrefab.getNextID()).getInt());
		setCreativeTab(CreativeTabs.tabBlock);
		setIconMax(2);
	}
	
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityScanner();
	}

}
