package dark.mining.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraftforge.common.Configuration;
import dark.core.common.DMCreativeTab;
import dark.core.prefab.machine.BlockMachine;
import dark.mining.common.privateutils.ModConfig;

/** @author Archadia */
public class BlockMM extends BlockMachine
{
    private Icon[] icons = new Icon[1];
    private String iconPath;
    private boolean isMultiTextured = false;

    public BlockMM(String blockName, Material material)
    {
        super(ModConfig.getConfig("Objects"), blockName, material);
        setCreativeTab(CreativeTabs.tabBlock);

    }

    protected String getName()
    {
        return this.getUnlocalizedName().replace("tile.", "");
    }

    protected void setBlockPath(String path)
    {
        iconPath = "MM_" + path;
    }

    protected void setIconMax(int max)
    {
        icons = new Icon[max];
        isMultiTextured = true;
    }

    @Override
    public void registerIcons(IconRegister ir)
    {
        if (!isMultiTextured)
        {
            this.blockIcon = ir.registerIcon("mechanizedmining:" + iconPath);
        }
        else
        {
            this.icons[0] = ir.registerIcon("mechanizedmining:MM_mechanizedBlock_side");
            this.icons[1] = ir.registerIcon("mechanizedmining:MM_mechanizedBlock_top");
        }
    }

    @Override
    public Icon getIcon(int side, int meta)
    {
        if (isMultiTextured)
        {
            if (side > 1)
            {
                return icons[0];
            }
            return icons[1];
        }
        return this.blockIcon;
    }
}
