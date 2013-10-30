package dark.mining.common.gas.system.core;

import dark.mining.common.gas.machines.interfaces.IGasTank;

/**
 * @author Archadia
 *
 */
public final class GasDisplay {

    public final GasStack gas;
    public final float density;

    public GasDisplay(IGasTank tank)
    {
        this.gas = tank.getStack();
        this.density = tank.getStack().getGas().getDensity();
    }
}
