package dark.mining.machines.block;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

import com.builtbroken.common.Pair;

import dark.mining.MechanizedMining;
import dark.mining.block.BlockMM;
import dark.mining.machines.tile.TileScanner;

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
        return new TileScanner();
    }

    @Override
    public void getTileEntities(int blockID, Set<Pair<String, Class<? extends TileEntity>>> list)
    {
        list.add(new Pair<String, Class<? extends TileEntity>>("TileOreScanner", TileScanner.class));
    }
}
