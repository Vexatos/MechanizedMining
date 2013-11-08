package dark.mining.privateutils;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/** @author Archadia */
public class WorldHelper
{

    public static Block getBlock(World world, int x, int y, int z)
    {
        int blockID = world.getBlockId(x, y, z);

        for (Block block : Block.blocksList)
        {
            if (block.blockID == blockID)
            {
                return block;
            }
        }
        return null;
    }
}
