package dark.mining.vehicles;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import dark.core.interfaces.IExternalInv;
import dark.core.interfaces.IInvBox;
import dark.core.prefab.entities.EntityVehicle;
import dark.core.prefab.invgui.InvChest;

/** Simple extension of the test car to provide small base inventory and options for upgrades.
 * Upgrades should be more inventory space, chargers for tools, or mobile machines for processing.
 * This truck should be designed to be a platform for future changes, and upgrades. Meaning very
 * little of it should be designed for one task. However, its look should be that of a simple seater
 * truck with a 2 block bed in the back. Both a tracked and wheeled version should be made for
 * players to pick from.
 * 
 * @author DarkGuardsman */
public class EntityMiningTruck extends EntityVehicle implements IExternalInv
{
    protected IInvBox inventory;

    public EntityMiningTruck(World world)
    {
        super(world);
    }

    public EntityMiningTruck(World world, double xx, double yy, double zz)
    {
        super(world, xx, yy, zz);
    }

    @Override
    public void updateClients()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void dropAsItem()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public IInvBox getInventory()
    {
        if (inventory == null)
        {
            this.inventory = new InvChest(this, 10);
        }
        return this.inventory;
    }

    @Override
    public boolean canStore(ItemStack stack, int slot, ForgeDirection side)
    {
        return false;
    }

    @Override
    public boolean canRemove(ItemStack stack, int slot, ForgeDirection side)
    {
        return false;
    }

}
