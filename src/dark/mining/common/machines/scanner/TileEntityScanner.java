package dark.mining.common.machines.scanner;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.common.privateutils.TripleContainer;

/** @author Archadia */
public class TileEntityScanner extends TileEntityEnergyMachine
{

    private ArrayList<Integer> validTarget = new ArrayList<Integer>();
    private TripleContainer<Integer, Integer, Integer> usedTarget = new TripleContainer<Integer, Integer, Integer>();

    public boolean enabled = true;

    Vector3[] vex = new Vector3[3];
    
    public TileEntityScanner()
    {
        super(0.5f); //500W
        
    }

    @Override
    public void updateEntity()
    {
        if (!worldObj.isRemote)
        {
            for (byte i = 0; i < 5 && this.canFunction(); i++)
            {
                scanArea();
            }
        }
    }

    @Override
    public boolean canFunction()
    {
        return super.canFunction() && this.enabled;
    }

    /** Scans the area for valid blocks */
    protected void scanArea()
    {
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
                usedTarget.put(targetX, targetY, targetZ);
            }
        }
    }

    protected boolean isTargetValid(int id)
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

    protected void printResult(int id)
    {
        System.out.println("Found " + id);
    }

    @Override
    public boolean canConnect(ForgeDirection dir)
    {
        return true;
    }

}
