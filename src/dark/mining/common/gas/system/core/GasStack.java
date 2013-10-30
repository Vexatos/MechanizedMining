package dark.mining.common.gas.system.core;

import java.util.Locale;

import net.minecraft.nbt.NBTTagCompound;
import dark.mining.common.gas.system.GasRegistry;

/**
 * @author Archadia
 *
 */
public class GasStack {

	public int stackSize;

	public int gasID;
	
	private Gas gas;
	
	public String name;
	 
	public NBTTagCompound stackTagCompound;
	
    public GasStack(Gas gas)
    {
        this(gas.gasID, 1);
    }

    public GasStack(Gas gas, int amt)
    {
        this(gas.gasID, amt);
    }

    public GasStack(int id, int amt)
    {
        this.gasID = id;
        this.stackSize = amt;
        gas = GasRegistry.getGas(id);
        name = GasRegistry.getGasName(id);
        if(name == null) {
        	System.out.println("NULL!");
        } else {
        	System.out.println(name);
        }
    }
    
    public GasStack splitStack(int par1)
    {
        GasStack gasstack = new GasStack(this.gasID, par1);

        if (this.stackTagCompound != null)
        {
        	gasstack.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
        }

        this.stackSize -= par1;
        return gasstack;
    }
    
    public Gas getGas() {
    	return gas;
    }
    
    public GasStack copy()
    {
    	GasStack gasstack = new GasStack(this.gasID, this.stackSize);

        if (this.stackTagCompound != null)
        {
        	gasstack.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
        }

        return gasstack;
    }
    
    public boolean isGasEqual(GasStack gasstack)
    {
        return this.gasID == gasstack.gasID;
    }
    
    public boolean hasTagCompound()
    {
        return this.stackTagCompound != null;
    }

    public NBTTagCompound getTagCompound()
    {
        return this.stackTagCompound;
    }
    
    public void setTagCompound(NBTTagCompound par1NBTTagCompound)
    {
        this.stackTagCompound = par1NBTTagCompound;
    }
    
    public static GasStack loadGas(NBTTagCompound nbt)
    {
        if (nbt == null)
        {
            return null;
        }
        String gasName = nbt.getString("GasName");
        if (gasName == null)
        {
        	gasName = nbt.hasKey("GasName") ? nbt.getString("GasName").toLowerCase(Locale.ENGLISH) : null;
        	gasName = "fluidstuff";
        }

        if (gasName ==null || GasRegistry.getGas(gasName) == null)
        {
            return null;
        }
        GasStack stack = new GasStack(GasRegistry.getGasID(gasName), nbt.getInteger("Amount"));

        if (nbt.hasKey("Tag"))
        {
            stack.stackTagCompound = nbt.getCompoundTag("Tag");
        }
        else if (nbt.hasKey("extra"))
        {
            stack.stackTagCompound = nbt.getCompoundTag("extra");
        }
        return stack;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setString("GasName", GasRegistry.getGasName(gasID));
        nbt.setInteger("Amount", stackSize);

        if (stackTagCompound != null)
        {
            nbt.setTag("Tag", stackTagCompound);
        }
        return nbt;
    }
}
