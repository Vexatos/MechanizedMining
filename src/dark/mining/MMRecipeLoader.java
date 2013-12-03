package dark.mining;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import dark.core.common.CoreRecipeLoader;
import dark.core.common.RecipeLoader;
import dark.core.common.items.EnumMaterial;
import dark.core.common.items.EnumOrePart;
import dark.core.common.items.ItemParts.Parts;
import dark.core.prefab.fluids.Gas;
import dark.mining.item.tool.ItemMiningLaser;

public class MMRecipeLoader extends RecipeLoader
{
    public static Block machineScanner, machineFracker, rubble, frackingPipe, machineApertureExc, laserSentry, laserDrill;
    public static Item toolDrill, toolHoleCreator, toolMiningLaser;
    public static Gas methane;
    
    @Override
    public void loadRecipes()
    {
        super.loadRecipes();
        if (toolMiningLaser instanceof ItemMiningLaser)
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(toolMiningLaser, 1, 0), new Object[] { "LLS", "SCS", "CBW", 'B', "Battery", 'C', circuit2, 'W', "copperWire", 'L', new ItemStack(CoreRecipeLoader.itemParts, 4, Parts.LASER.ordinal()), 'S', EnumMaterial.getStack(EnumMaterial.ALUMINIUM, EnumOrePart.INGOTS, 1) }));

        }
    }
}
