package dark.mining.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.mining.MMRecipeLoader;

@SideOnly(Side.CLIENT)
public class MMBlockRenderingHandler implements ISimpleBlockRenderingHandler
{
    private static MMBlockRenderingHandler instance = new MMBlockRenderingHandler();
    public static final int BLOCK_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();

    public static MMBlockRenderingHandler instance()
    {
        if (instance == null)
        {
            instance = new MMBlockRenderingHandler();
        }
        return instance;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        GL11.glPushMatrix();
        if (MMRecipeLoader.frackingPipe != null && block.blockID == MMRecipeLoader.frackingPipe.blockID)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(RenderFrackingPipe.TEXTURE);
            GL11.glTranslatef(0, 1F, 0);
            GL11.glScalef(1.0F, -1F, -1F);
            RenderFrackingPipe.model.renderAll();
        }
        else if (MMRecipeLoader.laserSentry != null && block.blockID == MMRecipeLoader.laserSentry.blockID)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(RenderMiningLaser.TEXTURE);
            GL11.glTranslatef(0, 1.7F, 0);
            GL11.glScalef(1.0F, -1F, -1F);
            GL11.glRotatef(180, 0, 1, 0);
            RenderMiningLaser.model.renderAll();
        }
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory()
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return BLOCK_RENDER_ID;
    }
}
