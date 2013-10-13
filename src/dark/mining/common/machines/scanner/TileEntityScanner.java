package dark.mining.common.machines.scanner;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraftforge.common.ForgeDirection;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.TripleContainer;

/** @author Archadia */
public class TileEntityScanner extends TileEntityEnergyMachine
{

    private ArrayList<Integer> validTarget = new ArrayList<Integer>();
    private TripleContainer<Integer, Integer, Integer> usedTarget = new TripleContainer<Integer, Integer, Integer>();

    public boolean enabled = true;

    @Override
    public void updateEntity()
    {
        short tries = 0;
        if (!worldObj.isRemote)
        {
            while (this.getEnergyStored() > 5000 && enabled)
            {
                tries++;
                if (tries > 5)
                {
                    return;
                }

                scanArea();
            }
        }
    }

    private void scanArea()
    {
        consumePower(100, true);

        int targetX = xCoord - 150;
        int targetZ = zCoord - 150;
        int targetY = this.worldObj.rand.nextInt(15) + 5;

        targetX += (this.worldObj.rand.nextInt(300));
        targetZ += (this.worldObj.rand.nextInt(300));

        int targetId = worldObj.getBlockId(targetX, targetY, targetZ);

        if (!usedTarget.getAList().contains(targetX) && !usedTarget.getBList().contains(targetY) && !usedTarget.getCList().contains(targetZ))
        {
            if (isTargetValid(targetId))
            {
                printResult(targetId);
                consumePower(50, true);
                usedTarget.put(targetX, targetY, targetZ);
            }
        }
    }

    private boolean isTargetValid(int id)
    {
        for (Block block : Block.blocksList)
        {
            if (block.blockID == id)
            {
                if (block.getUnlocalizedName().contains("ore"))
                    return true;
            }
            return false;
        }
        return false;
    }

    private void printResult(int id)
    {
        System.out.println("Found " + id);
    }

    @Override
    public float getRequest(ForgeDirection dir)
    {
        return 5000;
    }

    @Override
    public boolean canConnect(ForgeDirection dir)
    {
        return true;
    }

}
