package dark.mining.vehicles;

import net.minecraft.world.World;
import dark.core.prefab.vehicles.EntityVehicle;

/** Platform for heavy mining gear to be used from. Should look like an orange no-armor tank about 4
 * blocks wide, and 6 blocks long.
 * 
 * @author DarkGuardsman */
public class EntityMiningTank extends EntityVehicle
{
    public EntityMiningTank(World world)
    {
        super(world);
    }

    public EntityMiningTank(World world, double xx, double yy, double zz)
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
}
