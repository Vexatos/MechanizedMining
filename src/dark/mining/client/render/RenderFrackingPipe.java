package dark.mining.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import dark.core.prefab.ModPrefab;
import dark.mining.MechanizedMining;
import dark.mining.client.model.ModelFrackingPipe;

/** @author Archadia */
public class RenderFrackingPipe extends TileEntitySpecialRenderer
{

    public static final ModelFrackingPipe model = new ModelFrackingPipe();
    public static final String TEXTURE = "frackingpipe.png";

    public void renderTileEntityAt(TileEntity tileEntity, double d, double d1, double d2, float f)
    {
        bindTexture(new ResourceLocation(MechanizedMining.instance.DOMAIN, ModPrefab.MODEL_DIRECTORY + TEXTURE));

        GL11.glPushMatrix();
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);

        model.renderAll();
        GL11.glPopMatrix(); // end
    }
}
