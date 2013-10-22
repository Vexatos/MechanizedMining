package dark.mining.common.block;

import net.minecraft.creativetab.CreativeTabs;
import universalelectricity.core.UniversalElectricity;

/** @author Archadia */
public class BlockRubble extends BlockMM
{

    /** @param blockName
     * @param material */
    public BlockRubble()
    {
        super("World_Rubble", UniversalElectricity.machine);
        setBlockPath("rubble");
        setCreativeTab(CreativeTabs.tabBlock);
    }
}