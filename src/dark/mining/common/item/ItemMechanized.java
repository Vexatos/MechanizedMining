package dark.mining.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import universalelectricity.core.item.ItemElectric;
import dark.core.prefab.ModPrefab;
import dark.mining.common.privateutils.ModConfig;

/**
 * @author Archadia
 *
 */
public class ItemMechanized extends ItemElectric {
	
    private String iconPath;

    public ItemMechanized(String itemName)
    {
        super(ModConfig.getConfig("Objects").getItem(itemName, ModPrefab.getNextID()).getInt());
    }

    protected String getName()
    {
        return this.getUnlocalizedName().replace("item.", "");
    }

    protected void setItemPath(String path)
    {
        iconPath = "MM_" + path;
    }

    @Override
    public void registerIcons(IconRegister ir)
    {
    	itemIcon = ir.registerIcon("mechanizedmining:" + iconPath);
    }

	@Override
	public float getMaxElectricityStored(ItemStack theItem) {
		return 0;
	}
}
