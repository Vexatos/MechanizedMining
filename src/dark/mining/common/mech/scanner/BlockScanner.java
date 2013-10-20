package dark.mining.common.mech.scanner;

import java.util.Set;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

import com.builtbroken.common.Pair;

import dark.mining.common.MechanizedMining;
import dark.mining.common.block.BlockMM;

/** @author Archadia */
public class BlockScanner extends BlockMM
{

    public BlockScanner()
    {
        super("Machine_OreScanner", UniversalElectricity.machine);
        setIconMax(2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int a, float b, float c, float d)
    {
        player.openGui(MechanizedMining.instance, 0, world, x, y, z);

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
