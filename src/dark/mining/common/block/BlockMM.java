package dark.mining.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import dark.core.prefab.machine.BlockMachine;
import dark.mining.common.privateutils.ModConfig;

/** @author Archadia */
public class BlockMM extends BlockMachine
{
    private Icon[] icons = new Icon[0];
    private String iconPath;

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
    }

    @Override
    public void registerIcons(IconRegister ir)
    {
    	switch(icons.length) {
	    	case 0:
	            this.blockIcon = ir.registerIcon("mechanizedmining:" + iconPath);
	            break;
	    	case 2:
	    		this.icons[0] = ir.registerIcon("mechanizedmining:MM_mechanizedBlock_side");
	            this.icons[1] = ir.registerIcon("mechanizedmining:MM_mechanizedBlock_top");
	            break;
    	}
    }

    @Override
    public Icon getIcon(int side, int meta) {
    	switch(icons.length) {
	    	case 0:
	            return this.blockIcon;
	    	case 2:
	            if (side > 1) {
	                return icons[0];
	            }
	            return icons[1];
	    	default:
	    		return this.blockIcon;
    	}
    }
}
