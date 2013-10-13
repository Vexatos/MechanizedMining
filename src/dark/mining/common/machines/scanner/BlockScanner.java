package dark.mining.common.machines.scanner;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dark.core.prefab.ModPrefab;
import dark.mining.common.MMConfig;
import dark.mining.common.MechanizedMining;
import dark.mining.common.block.BlockMM;

/** @author Archadia */
public class BlockScanner extends BlockMM
{

    public BlockScanner()
    {
        super(MMConfig.config.getBlock("scanner", ModPrefab.getNextID()).getInt());
        setCreativeTab(CreativeTabs.tabBlock);
        setIconMax(2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int a, float b, float c, float d)
    {
        System.out.println("PROOF!");

        player.openGui(MechanizedMining.instance, 0, world, x, y, z);
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityScanner();
    }
}
