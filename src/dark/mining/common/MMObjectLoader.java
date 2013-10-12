package dark.mining.common;

import java.util.HashMap;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import dark.core.registration.ModObjectRegistry;
import dark.mining.common.block.BlockMM;
import dark.mining.common.block.BlockMechanized;

/**
 * @author Archadia
 *
 */
public class MMObjectLoader {
	
	public HashMap<Block, String> nameList = new HashMap<Block, String>();
	
	public void addMMBlock(Block block, String name, String modID, Class<? extends BlockMM> blockClass) {
		block = ModObjectRegistry.createNewBlock(name, modID, blockClass);
		block.setUnlocalizedName(name);
		nameList.put(block, name);
	}
	
	public String getBlockName(Block block) {
		return nameList.get(block);
	}
}
