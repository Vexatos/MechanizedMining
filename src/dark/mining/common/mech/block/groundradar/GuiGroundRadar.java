package dark.mining.common.mech.block.groundradar;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import dark.mining.common.mech.block.scanner.ContainerScanner;
import dark.mining.common.mech.block.scanner.TileEntityScanner;

/**
 * @author Archadia
 *
 */
public class GuiGroundRadar extends GuiContainer
{

    private static TileEntityGroundRadar tileINV;

    public GuiGroundRadar(InventoryPlayer par1InventoryPlayer, TileEntityGroundRadar tile)
    {
        super(new ContainerGroundRadar(par1InventoryPlayer, tile));
        tileINV = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("mechanizedmining", "textures/guis/dscreen.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
