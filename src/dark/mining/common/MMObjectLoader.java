package dark.mining.common;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import dark.core.registration.ModObjectRegistry;

/** @author Archadia */
public class MMObjectLoader
{
    public HashMap<Block, String> bnameList = new HashMap<Block, String>();
    private ArrayList<Block> list = new ArrayList<Block>();

    private MMConfig config = new MMConfig();

    /** Method to add a MM block, uses CoreMachines' loader mixed with Archadia's personal loader.
     * 
     * @param name
     * @param modID
     * @param blockClass
     * @param canDisable */
    public void addMMObject(String name, Class<? extends Block> blockClass, Class<? extends TileEntity> tileClass)
    {
        Block block;
        int blockID;

        block = ModObjectRegistry.createNewBlock(name, MechanizedMining.MOD_ID, blockClass, true);
        block.setUnlocalizedName(name);
        bnameList.put(block, name);
        list.add(block);

        if (tileClass != null)
        {
            String tilename = "tileEntity" + name.toUpperCase();
            GameRegistry.registerTileEntity(tileClass, tilename);
        }
    }

    /** Method to get block via name
     * 
     * @param blockName
     * @return Block requested */
    public Block getBlock(String blockName)
    {
        for (Block block : list)
        {
            String name = block.getUnlocalizedName().replace("tile.", "");
            if (name == blockName)
            {
                return block;
            }
        }
        return null;
    }

    /** Method to get block via id
     * 
     * @param blockID
     * @return Block requested */
    public Block getBlock(int blockID)
    {
        for (Block block : list)
        {
            if (blockID == block.blockID)
            {
                return block;
            }
        }
        return null;
    }
}
