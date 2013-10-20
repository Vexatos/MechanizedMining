package dark.mining.common.mech.scanner;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/** @author Archadia */
public class GuiScanner extends GuiContainer
{

    private static TileEntityScanner tileINV;

    public GuiScanner(InventoryPlayer par1InventoryPlayer, TileEntityScanner tile)
    {
        super(new ContainerScanner(par1InventoryPlayer, tile));
        tileINV = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("mechanizedmining", "textures/guis/scanner.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
