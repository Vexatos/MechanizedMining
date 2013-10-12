package dark.mining.common.block;

import dark.core.prefab.ModPrefab;
import dark.mining.common.MMConfig;
import net.minecraft.creativetab.CreativeTabs;

/**
 * @author Archadia
 *
 */
public class BlockMechanized extends BlockMM {

	public BlockMechanized() {
		super(MMConfig.config.getBlock("mechanizedBlock", ModPrefab.getNextID()).getInt());
		setCreativeTab(CreativeTabs.tabBlock);
		setIconMax(2);
	}
}
