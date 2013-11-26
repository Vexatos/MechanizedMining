package dark.mining.machines.block;

import java.util.Set;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

import com.builtbroken.common.Pair;

import dark.mining.block.BlockMM;
import dark.mining.machines.tile.TileApertureExcavator;

/** @author Archadia */
public class BlockApertureExcavator extends BlockMM
{

    public BlockApertureExcavator()
    {
        super("Machine_ApertureExcavator", UniversalElectricity.machine);
        setIconMax(2);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileApertureExcavator();
    }

    @Override
    public void getTileEntities(int blockID, Set<Pair<String, Class<? extends TileEntity>>> list)
    {
        list.add(new Pair<String, Class<? extends TileEntity>>("TileApertureExcavator", TileApertureExcavator.class));
    }
}
