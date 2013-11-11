package dark.mining.block;

import java.util.List;

import com.builtbroken.common.Pair;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

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
}
