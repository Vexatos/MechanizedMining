package dark.mining.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import dark.core.prefab.ModPrefab;
import dark.mining.MechanizedMining;
import dark.mining.client.model.ModelLaserTile;
import dark.mining.machines.tile.TileEntityMiningLaser;

/** @author Darkguardsman */
public class RenderMiningLaser extends TileEntitySpecialRenderer
{

    public static final ModelLaserTile model = new ModelLaserTile();
    public static final ResourceLocation TEXTURE = new ResourceLocation(MechanizedMining.instance.DOMAIN, ModPrefab.MODEL_DIRECTORY + "LaserTile.png");

    public void renderTileEntityAt(TileEntity tileEntity, double d, double d1, double d2, float f)
    {
        bindTexture(TEXTURE);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        if (tileEntity instanceof TileEntityMiningLaser)
        {
            float yaw = ((TileEntityMiningLaser) tileEntity).yaw - 90;
            GL11.glRotatef(yaw, 0, 1, 0);
        }
        model.renderAll();
        GL11.glPopMatrix(); // end
    }
}
