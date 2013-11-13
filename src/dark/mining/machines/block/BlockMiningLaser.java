package dark.mining.machines.block;

import java.util.Set;

import com.builtbroken.common.Pair;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dark.core.client.renders.BlockRenderingHandler;
import dark.mining.block.BlockMM;
import dark.mining.client.render.MMBlockRenderingHandler;
import dark.mining.machines.tile.TileApertureExcavator;
import dark.mining.machines.tile.laser.TileLaserSentry;

/** Mining laser Prototype mainly used for crafting but can be used in the same way as Excavator.
 * Creates four lasers from the side it is pointing in to mine away blocks
 * 
 * @author DarkGuardsman */
public class BlockMiningLaser extends BlockMM
{
    public BlockMiningLaser()
    {
        super("LaserMiner", Material.iron);
        setIconMax(2);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileLaserSentry();
    }

    @Override
    public void getTileEntities(int blockID, Set<Pair<String, Class<? extends TileEntity>>> list)
    {
        list.add(new Pair<String, Class<? extends TileEntity>>("TileMiningLaser", TileLaserSentry.class));
    }

    @Override
    public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            TileEntity ent = world.getBlockTileEntity(x, y, z);
            if (ent instanceof TileLaserSentry)
            {
                ((TileLaserSentry) ent).rotateYaw(-10);
            }
        }
        return false;
    }

    @Override
    public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            TileEntity ent = world.getBlockTileEntity(x, y, z);
            if (ent instanceof TileLaserSentry)
            {
                ((TileLaserSentry) ent).rotateYaw(10);
            }
        }
        return false;
    }

    public int getRenderType()
    {
        return MMBlockRenderingHandler.BLOCK_RENDER_ID;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

}
