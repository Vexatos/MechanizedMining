package dark.mining.common.machines.scanner;

import java.util.Set;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

import com.builtbroken.common.Pair;

import dark.mining.common.MechanizedMining;
import dark.mining.common.block.BlockMechanized;

/** @author Archadia */
public class BlockScanner extends BlockMechanized
{

    public BlockScanner()
    {
        super("OreScanner", UniversalElectricity.machine);
        setCreativeTab(CreativeTabs.tabBlock);
        setIconMax(2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int a, float b, float c, float d)
    {
        System.out.println("PROOF!");

        //player.openGui(MechanizedMining.instance, 0, world, x, y, z);

        //temp
        if(world.getBlockTileEntity(x, y, z) instanceof TileEntityScanner)
        {
            ((TileEntityScanner) world.getBlockTileEntity(x, y, z)).scanArea();
        }

        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityScanner();
    }

    @Override
    public void getTileEntities(int blockID, Set<Pair<String, Class<? extends TileEntity>>> list)
    {
        list.add(new Pair<String, Class<? extends TileEntity>>("TileOreScanner",TileEntityScanner.class));
    }
}
