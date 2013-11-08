package dark.mining.item.tool;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import universalelectricity.core.item.ItemElectric;
import dark.core.prefab.ModPrefab;
import dark.mining.privateutils.ModConfig;

/** @author Archadia */
public abstract class ItemElectricTool extends ItemElectric
{

    private String iconPath;
    private boolean hasAbility = false;

    public ItemElectricTool(String itemName, boolean ability)
    {
        super(ModConfig.getConfig("Objects").getItem(itemName, ModPrefab.getNextID()).getInt());
        setCreativeTab(CreativeTabs.tabBlock);
        setUnlocalizedName(itemName);
        hasAbility = ability;
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
}
