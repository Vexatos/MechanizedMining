package dark.mining.common.machines.groundradar;

import java.util.Set;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

import com.builtbroken.common.Pair;

import dark.mining.common.MechanizedMining;
import dark.mining.common.block.BlockMechanized;
import dark.mining.common.machines.scanner.TileEntityScanner;

/**
 * @author Archadia
 *
 */
public class BlockGroundRadar extends BlockMechanized
{

    public BlockGroundRadar() {
        super("GroundRadar", UniversalElectricity.machine);
        setCreativeTab(CreativeTabs.tabBlock);
        setIconMax(2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int a, float b, float c, float d)
    {
        player.openGui(MechanizedMining.instance, 1, world, x, y, z);

        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityGroundRadar();
    }

    @Override
    public void getTileEntities(int blockID, Set<Pair<String, Class<? extends TileEntity>>> list)
    {
        list.add(new Pair<String, Class<? extends TileEntity>>("TileGroundRadar",TileEntityGroundRadar.class));
    }
}
