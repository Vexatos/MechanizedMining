package dark.mining.machines.block;

import java.util.Set;

import universalelectricity.core.UniversalElectricity;

import com.builtbroken.common.Pair;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dark.mining.MechanizedMining;
import dark.mining.block.BlockMM;
import dark.mining.machines.tile.TileFracker;

/**
 * @author Archadia
 *
 */
public class BlockFracker extends BlockMM
{

    public BlockFracker()
    {
        super("Machine_Fracker", UniversalElectricity.machine);
        setIconMax(2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int a, float b, float c, float d)
    {
      //  player.openGui(MechanizedMining.instance, 1, world, x, y, z);
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileFracker();
    }

    @Override
    public void getTileEntities(int blockID, Set<Pair<String, Class<? extends TileEntity>>> list)
    {
        list.add(new Pair<String, Class<? extends TileEntity>>("TileFracker", TileFracker.class));
    }
}
