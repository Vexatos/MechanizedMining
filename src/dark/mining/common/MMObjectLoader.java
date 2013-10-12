package dark.mining.common;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import dark.core.registration.ModObjectRegistry;
import dark.mining.common.block.BlockMM;

/**
 * @author Archadia
 *
 */
public class MMObjectLoader {
	public HashMap<Block, String> bnameList = new HashMap<Block, String>();
	private ArrayList<Block> list = new ArrayList<Block>();
	
	private MMConfig config = new MMConfig();
	
	/**
	 * Method to add a MM block, uses CoreMachines' loader mixed with Archadia's personal loader.
	 * @param name
	 * @param modID
	 * @param blockClass
	 * @param canDisable
	 */
	public void addMMObject(String name, String modID, Class <? extends Block> blockClass, boolean canDisable) {
		Block block;
		int blockID;
		
		block = ModObjectRegistry.createNewBlock(name, modID, blockClass, canDisable);
		block.setUnlocalizedName(name);
		bnameList.put(block, name);
		list.add(block);
	}
	
	/**
	 * Method to get block via name
	 * @param blockName
	 * @return Block requested
	 */
	public Block getBlock(String blockName) {
		for(Block block : list) {
			String name = block.getUnlocalizedName().replace("tile.", "");
			if(name == blockName) {
				return block;
			}
		}
		return null;
	}
	
	/**
	 * Method to get block via id
	 * @param blockID
	 * @return Block requested
	 */
	public Block getBlock(int blockID) {
		for(Block block : list) {
			if(blockID == block.blockID) {
				return block;
			}
		}
		return null;
	}
}
