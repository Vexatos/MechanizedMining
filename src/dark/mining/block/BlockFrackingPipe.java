package dark.mining.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.builtbroken.common.Pair;

import dark.core.client.renders.BlockRenderingHandler;
import dark.mining.machines.tile.TileFrackingPipe;

/**
 * @author Archadia
 *
 */
public class BlockFrackingPipe extends BlockMM {

	public BlockFrackingPipe() {
		super("Fracking_Pipe", Material.wood);
	}
	
    public void getClientTileEntityRenderers(List<Pair<Class<? extends TileEntity>, TileEntitySpecialRenderer>> list) {
    	
    }
    
    public int getRenderType() {
		return BlockRenderingHandler.BLOCK_RENDER_ID;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileFrackingPipe();
	}
}
