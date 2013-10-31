package dark.mining.common.item;

import dark.core.prefab.ModPrefab;
import dark.mining.common.privateutils.ModConfig;
import net.minecraft.item.Item;

/**
 * @author Archadia
 *
 */
public class ItemGeneric extends Item {


	public ItemGeneric(String name) {
		super(ModConfig.getConfig("Objects").getItem(name, ModPrefab.getNextID()).getInt());
	}

}
