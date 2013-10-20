package dark.mining.common.privateutils;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import dark.core.registration.ModObjectRegistry;
import dark.mining.common.mech.block.scanner.BlockScanner;
import dark.mining.common.mech.item.ItemHandDrill;

/**
 * @author Archadia
 *
 */
public class ModObjectHandler {

	public static ArrayList<Object> objects = new ArrayList<Object>();
    
    /** Sorry darkcow, its just for now. I'll change it later */
	public static void addBlockObj(String name, Class<? extends Block> blockClass, boolean canDisable) {
		Block block = null;
		if(blockClass != null) {
			try {
				block = blockClass.newInstance();
			} catch(Exception e) { e.printStackTrace(); }
			if(block != null) {
				objects.add(block);
				GameRegistry.registerBlock(block, block.getUnlocalizedName());
				ModObjectRegistry.finishCreation(block, null);
			}
		}
	}
	
    /** Sorry darkcow, its just for now. I'll change it later */
	public static void addItemObj(String name, Class<? extends Item> itemClass, boolean canDisable) {
		Item item = null;
		if(itemClass != null) {
			try {
				item = itemClass.newInstance();
			} catch(Exception e) { e.printStackTrace(); }
			if(item != null) {
				objects.add(item);
			}
		}
	}
}
