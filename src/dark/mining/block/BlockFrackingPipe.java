package dark.mining.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.builtbroken.common.Pair;

import dark.mining.client.render.MMBlockRenderingHandler;
import dark.mining.machines.tile.fracker.TileFrackingPipe;

/** @author Archadia */
public class BlockFrackingPipe extends BlockMM
{

    public BlockFrackingPipe()
    {
        super("Fracking_Pipe", Material.wood);
    }

    @Override
    public void getClientTileEntityRenderers(List<Pair<Class<? extends TileEntity>, TileEntitySpecialRenderer>> list)
    {

    }

    @Override
    public int getRenderType()
    {
        return MMBlockRenderingHandler.BLOCK_RENDER_ID;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileFrackingPipe();
    }
}
