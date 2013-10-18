package dark.mining.common.mech.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import dark.mining.common.item.ItemMechanized;

/**
 * @author Archadia
 *
 */
public class ItemHandDrill                                                                               extends ItemMechanized {

	public ItemHandDrill() {
		super("HandDrill", true);
	}
	
	@Override
	public float getMaxElectricityStored(ItemStack theItem) {
		return 10000;
	}

	@Override
	public void useAbility() {
		
	}
}
