package dark.mining.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import dark.core.prefab.ModPrefab;
import dark.mining.privateutils.ModConfig;

/** @author Archadia */
public class ItemGeneric extends Item
{

    private String iconPath;

    public ItemGeneric(String name)
    {
        super(ModConfig.getConfig("Objects").getItem(name, ModPrefab.getNextID()).getInt());
    }

    protected String getName()
    {
        return this.getUnlocalizedName().replace("item.", "");
    }

    @Override
    public void registerIcons(IconRegister ir)
    {
        itemIcon = ir.registerIcon("mechanizedmining:" + iconPath);
    }

    protected void setItemPath(String path)
    {
        iconPath = "MM_" + path;
    }
}
