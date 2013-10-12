package dark.mining.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import dark.core.prefab.ModPrefab;
import dark.mining.common.MMConfig;

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

}
