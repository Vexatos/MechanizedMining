package dark.mining.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dark.core.client.renders.BlockRenderingHandler;
import dark.mining.CommonProxy;
import dark.mining.MMRecipeLoader;
import dark.mining.MechanizedMining;
import dark.mining.client.gui.GuiScanner;
import dark.mining.client.render.MMBlockRenderingHandler;
import dark.mining.client.render.RenderFrackingPipe;
import dark.mining.client.render.RenderMiningLaser;
import dark.mining.client.render.RenderMiningLaserGun;
import dark.mining.machines.tile.TileFrackingPipe;
import dark.mining.machines.tile.TileScanner;
import dark.mining.machines.tile.laser.TileLaserMiner;

/** @author Archadia */
public class ClientProxy extends CommonProxy
{
    @Override
    public void init()
    {
        super.init();
        RenderingRegistry.registerBlockHandler(new MMBlockRenderingHandler());
        if (MMRecipeLoader.toolMiningLaser != null)
        {
            MinecraftForgeClient.registerItemRenderer(MMRecipeLoader.toolMiningLaser.itemID, new RenderMiningLaserGun());
        }
        if (MMRecipeLoader.frackingPipe != null)
        {
        	ClientRegistry.bindTileEntitySpecialRenderer(TileFrackingPipe.class, new RenderFrackingPipe());
        }
        if (MMRecipeLoader.miningLaser != null)
        {
            ClientRegistry.bindTileEntitySpecialRenderer(TileLaserMiner.class, new RenderMiningLaser());
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        switch (ID)
        {
            case 0:
                return new GuiScanner(player.inventory, (TileScanner) tile_entity);
        }
        return null;
    }
}
