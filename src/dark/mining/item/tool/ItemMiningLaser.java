package dark.mining.item.tool;

import dark.core.interfaces.IExtraInfo.IExtraItemInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

/** @author Archadia */
public class ItemMiningLaser extends ItemElectricTool implements IExtraItemInfo
{
    float batterySize = 100;
    float wattPerShot = 1;
    float damageToEntities = 5;
    int blockRange = 50;
    int firingDelay = 5;

    public ItemMiningLaser()
    {
        super("MiningLaser", true);
        this.setMaxStackSize(1);
    }

    //TODO right click to aim and change up, left click to fire, with no swinging
    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        return false;
    }

    @Override
    public float getMaxElectricityStored(ItemStack theItem)
    {
        return 0;
    }

    @Override
    public boolean hasExtraConfigs()
    {
        return true;
    }

    @Override
    public void loadExtraConfigs(Configuration config)
    {
        this.blockRange = config.get("Laser", "Range", this.blockRange).getInt(this.blockRange);
        this.firingDelay = config.get("Laser", "Delay", this.firingDelay).getInt(this.firingDelay);
        this.damageToEntities = (float) config.get("Laser", "Damage", this.damageToEntities).getDouble(this.damageToEntities);
        this.batterySize = (float) (config.get("Energy", "BatteryCap", this.batterySize * 1000).getDouble(this.batterySize * 1000) / 1000);
        this.wattPerShot = (float) (config.get("Energy", "FiringCost", this.wattPerShot * 1000).getDouble(this.wattPerShot * 1000) / 1000);

    }

    @Override
    public void loadOreNames()
    {
        OreDictionary.registerOre("MiningLaserGun", this);

    }

}
