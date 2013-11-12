package dark.mining.machines.tile;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.network.packet.Packet;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.MinecraftForge;
import universalelectricity.core.vector.Vector3;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.Player;
import dark.api.events.LaserEvent;
import dark.core.common.DarkMain;
import dark.core.helpers.RayTraceHelper;
import dark.core.network.PacketHandler;
import dark.core.prefab.TileDamageSource;
import dark.core.prefab.machine.TileEntityEnergyMachine;

/** @author DarkGuardsman */
public class TileEntityMiningLaser extends TileEntityEnergyMachine
{
    private Vector3 target;
    private Vector3 hit;
    private int hitTicks = 0;

    public TileEntityMiningLaser()
    {
        super(.1f);
    }

    @Override
    public boolean canFunction()
    {
        return super.canFunction() && this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (this.ticks % 3 == 0 && this.isFunctioning())
        {
            this.fireLaser();
        }
    }

    public void fireLaser()
    {
        float yaw = 0;
        float pitch = 90;
        float distacne = 20;
        Vector3 start = RayTraceHelper.getPosFromRotation(new Vector3(this).translate(0.5), .7, yaw, pitch);
        MovingObjectPosition hitPos = RayTraceHelper.ray_trace_do(this.worldObj, start.toVec3(), yaw, pitch, distacne, false);
        Vector3 hitSpot = RayTraceHelper.getPosFromRotation(new Vector3(this), distacne, yaw, pitch);
        //TODO fix sound
        if (hitPos != null)
        {
            LaserEvent event = new LaserEvent.LaserFireEvent(this, hitPos);
            MinecraftForge.EVENT_BUS.post(event);

            if (!worldObj.isRemote && !event.isCanceled())
            {
                if (hitPos.typeOfHit == EnumMovingObjectType.ENTITY && hitPos.entityHit != null)
                {
                    DamageSource damageSource = TileDamageSource.doLaserDamage(this);
                    hitPos.entityHit.attackEntityFrom(damageSource, 7);
                    hitPos.entityHit.setFire(5);
                }
                else if (hitPos.typeOfHit == EnumMovingObjectType.TILE)
                {
                    if (this.hit != null && this.hit.equals(new Vector3(hitPos)))
                    {
                        this.hitTicks++;
                        if (hitTicks >= 6)
                        {
                            this.hit.setBlock(this.worldObj, 0);
                        }
                    }
                    else
                    {
                        this.hitTicks = 1;
                        this.hit = new Vector3(hitPos);
                    }

                }

            }
            hitSpot = new Vector3(hitPos.hitVec);
        }
        DarkMain.proxy.renderBeam(this.worldObj, start, hitSpot, Color.ORANGE, 3);
    }

    public Vector3 getTarget()
    {
        return this.target;
    }

    public void setTarget(Vector3 vec)
    {
        if (!this.worldObj.isRemote)
        {
            this.sendPowerUpdate();
        }
    }
}
