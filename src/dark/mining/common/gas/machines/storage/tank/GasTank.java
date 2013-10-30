package dark.mining.common.gas.machines.storage.tank;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import universalelectricity.prefab.network.IPacketReceiver;

import com.google.common.io.ByteArrayDataInput;

import dark.mining.common.gas.machines.interfaces.IGasTank;
import dark.mining.common.gas.system.core.Gas;
import dark.mining.common.gas.system.core.GasDisplay;
import dark.mining.common.gas.system.core.GasStack;

/**
 * @author Archadia
 *
 */
public class GasTank implements IGasTank, IPacketReceiver {

	protected GasStack stack;
	protected int capacity;
    protected TileEntity tile;

    public GasTank(int cap) {
    	capacity = cap;
    }
    
    public GasTank readFromNBT(NBTTagCompound nbt)
    {
        if (!nbt.hasKey("Empty"))
        {
            GasStack gas = GasStack.loadGas(nbt);

            if (gas != null)
            {
                setGas(gas);
            }
        }
        return this;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        if (stack != null)
        {
        	stack.writeToNBT(nbt);
        }
        else
        {
            nbt.setString("Empty", "");
        }
        return nbt;
    }
    
    public void setGas(GasStack gas)
    {
        this.stack = gas;
    }

    public void setGasStored(Gas gas, int amt) {
    	if(gas != null) {
	    	if(getGas() == null || gas.getName() == this.stack.getGas().getName()) {
	    		if(getGasAmount() < capacity) {
	    			setGas(new GasStack(gas, getGasAmount() + amt));
	    		} else {
	    			return;
	    		}
	    	}
    	}
    }
    
    public Gas getGas() {
    	return stack.getGas();
    }

	
	@Override
	public int getGasAmount() {
		if(stack != null) {
			return stack.stackSize;
		}
		return 0;
	}

	@Override
	public GasDisplay getDisplayInfo() {
		return null;
	}

	@Override
	public void handlePacketData(INetworkManager network, int packetType,
			Packet250CustomPayload packet, EntityPlayer player,
			ByteArrayDataInput dataStream) {
	}

	@Override
	public GasStack getStack() {
		return stack;
	}
}
