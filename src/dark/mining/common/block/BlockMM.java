package dark.mining.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import dark.mining.common.MMObjectLoader;

/**
 * @author Archadia
 *
 */
public class BlockMM extends Block {

	MMObjectLoader objLoader = new MMObjectLoader();	
	/**
	 * @param par1
	 * @param par2Material
	 */
	public BlockMM(int i) {
		super(i, Material.iron);
	}
	
	public void registerIcons(IconRegister ir) {
		this.blockIcon = ir.registerIcon("mechanizedmining:" + objLoader.getBlockName(this));
	}
}
