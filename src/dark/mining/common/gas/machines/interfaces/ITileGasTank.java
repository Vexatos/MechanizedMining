package dark.mining.common.gas.machines.interfaces;

import net.minecraft.nbt.NBTTagCompound;
import dark.mining.common.gas.system.core.Gas;

/**
 * @author Archadia
 *
 */
public interface ITileGasTank {

    public void readFromNBT(NBTTagCompound tag);
    
    public void writeToNBT(NBTTagCompound tag);

	public void setGasStored(Gas gas, int amt);
}