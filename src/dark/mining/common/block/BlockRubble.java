package dark.mining.common.block;

import universalelectricity.core.UniversalElectricity;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * @author Archadia
 *
 */
public class BlockRubble extends BlockMM {

	/**
	 * @param blockName
	 * @param material
	 */
	public BlockRubble() {
		super("World_Rubble", UniversalElectricity.machine);
		setBlockPath("rubble");
		setCreativeTab(CreativeTabs.tabBlock);
	}
}