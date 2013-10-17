package dark.mining.common;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import dark.core.registration.ModObjectRegistry;
import dark.mining.common.mech.block.groundradar.BlockGroundRadar;
import dark.mining.common.mech.block.groundradar.ContainerGroundRadar;
import dark.mining.common.mech.block.groundradar.GuiGroundRadar;
import dark.mining.common.mech.block.groundradar.TileEntityGroundRadar;
import dark.mining.common.mech.block.scanner.BlockScanner;
import dark.mining.common.mech.block.scanner.ContainerScanner;
import dark.mining.common.mech.block.scanner.GuiScanner;
import dark.mining.common.mech.block.scanner.TileEntityScanner;

/** @author Archadia */
public class CommonProxy implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        switch (ID)
        {
            case 0:
                return new ContainerScanner(player.inventory, (TileEntityScanner) tile_entity);
            case 1:
                return new ContainerGroundRadar(player.inventory, (TileEntityGroundRadar) tile_entity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        switch (ID)
        {
            case 0:
                return new GuiScanner(player.inventory, (TileEntityScanner) tile_entity);
            case 1:
                return new GuiGroundRadar(player.inventory, (TileEntityGroundRadar) tile_entity);
        }
        return null;
    }
}
