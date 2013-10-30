package dark.mining.common.mech.gastank;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

import com.builtbroken.common.Pair;

import dark.mining.common.block.BlockMM;
import dark.mining.common.gas.system.GasRegistry;

/**
 * @author Archadia
 *
 */
public class BlockGasTank extends BlockMM {
	
	public BlockGasTank()
    {
        super("Machine_GasTank", UniversalElectricity.machine);
        setIconMax(2);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int a, float b, float c, float d)
    {
    	TileEntityGasTank tile = (TileEntityGasTank) world.getBlockTileEntity(x, y, z);
    	tile.setGasStored(GasRegistry.getGas("methane"), 100);
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityGasTank();
    }

    @Override
    public void getTileEntities(int blockID, Set<Pair<String, Class<? extends TileEntity>>> list)
    {
        list.add(new Pair<String, Class<? extends TileEntity>>("TileGasTank", TileEntityGasTank.class));
    }
}
