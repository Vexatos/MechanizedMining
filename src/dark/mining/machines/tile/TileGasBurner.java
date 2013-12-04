package dark.mining.machines.tile;

import java.util.HashSet;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import dark.core.prefab.fluids.EnumGas;
import dark.core.prefab.fluids.GasTank;
import dark.core.prefab.machine.TileEntityMachine;

/**
 * @author Archadia
 *
 */
public class TileGasBurner extends TileEntityMachine implements IFluidHandler {

	GasTank tank = new GasTank(5000);
	HashSet<Fluid> validFuel = new HashSet<Fluid>();

	public TileGasBurner() {
		validFuel.add(EnumGas.METHANE.getGas());
	}

	public boolean isGasValidFuel(Fluid fluid) {
		if(!fluid.isGaseous()) return false;
		if(!validFuel.contains(fluid)) return false;

		return true;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return null;
	}

}
