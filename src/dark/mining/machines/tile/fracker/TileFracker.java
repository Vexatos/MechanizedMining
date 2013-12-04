package dark.mining.machines.tile.fracker;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.FluidStack;
import universalelectricity.core.vector.Vector3;
import dark.core.basics.BlockGasOre;
import dark.core.prefab.fluids.EnumGas;
import dark.core.prefab.fluids.GasTank;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.MMRecipeLoader;

/** @author Archadia */
public class TileFracker extends TileEntityEnergyMachine
{
    GasTank tank = new GasTank(10000);

    private Vector3 target;

    public TileFracker()
    {
        super(0, 5);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (!worldObj.isRemote)
        {
            if (this.ticks % 20 == 0)
            {
                frack();
                System.out.println("Amt: " + tank.getFluidAmount());
            }
        }
    }

    public void frack()
    {
        if (target == null)
        {
            target = new Vector3(xCoord, yCoord, zCoord);
        }
        if (target.intY() > 0)
        {
            if (this.getEnergyStored() >= 4)
            {
                target.translate(Vector3.DOWN());

                int blockID = target.getBlockID(this.worldObj);
                Block block = Block.blocksList[blockID];
                if (block != null)
                {
                    if (block instanceof BlockGasOre)
                    {
                        tank.fill(new FluidStack(EnumGas.NATURAL_GAS.getGas(), 1000), true);
                    }
                    worldObj.setBlockToAir(target.intX(), target.intY(), target.intZ());
                    this.consumePower(2, true);
                }
                worldObj.setBlock(target.intX(), target.intY(), target.intZ(), MMRecipeLoader.frackingPipe.blockID);
                this.consumePower(0.5f, true);
            }
        }
    }
}