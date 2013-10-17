package dark.mining.common.block;

import net.minecraft.block.material.Material;
import dark.core.common.DMCreativeTab;
import dark.mining.common.MechanizedMining;
import dark.mining.common.privateutils.ModConfig;

/** @author Archadia */
public class BlockMechanized extends BlockMM
{

    public BlockMechanized(String blockName, Material material)
    {
        super(ModConfig.getConfig("Objects"), blockName, material);
        setCreativeTab(DMCreativeTab.tabIndustrial);
        setIconMax(2);
    }
}
