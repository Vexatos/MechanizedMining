package dark.mining.item.tool;

import net.minecraft.item.ItemStack;

/** @author Archadia */
public class ItemHandDrill extends ItemElectricTool
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

}
