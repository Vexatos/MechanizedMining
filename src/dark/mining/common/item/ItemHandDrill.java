package dark.mining.common.item;

import net.minecraft.item.ItemStack;

/** @author Archadia */
public class ItemHandDrill extends ItemMechanized
{

    public ItemHandDrill()
    {
        super("HandDrill", true);
    }

    @Override
    public float getMaxElectricityStored(ItemStack theItem)
    {
        return 10000;
    }

    @Override
    public void useAbility()
    {

    }
}
