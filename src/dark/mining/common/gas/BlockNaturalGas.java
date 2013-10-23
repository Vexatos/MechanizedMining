package dark.mining.common.gas;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.mining.common.block.BlockMM;
import universalelectricity.core.UniversalElectricity;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Archadia
 *
 */
public class BlockNaturalGas extends BlockMM {
	
	public BlockNaturalGas() {
		super("World_NaturalGas", UniversalElectricity.machine);
		setBlockPath("naturalgas");
        setCreativeTab(CreativeTabs.tabBlock);
	}

    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlocksPass()
    {
        return 1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
}
