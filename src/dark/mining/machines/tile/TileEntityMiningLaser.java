package dark.mining.machines.tile;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.network.packet.Packet;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.ForgeDirection;
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
    private float yaw = 0;
    private float pitch = 0;
    private float range = 20;
    private float powerDrain = .1f;

    @Override
    public boolean canFunction()
    {
        return super.canFunction();
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

    public void rotateYaw(float by)
    {
        this.yaw += by;
        if (!this.worldObj.isRemote)
        {
            PacketHandler.instance().sendPacketToClients(this.getDescriptionPacket(), this.worldObj, new Vector3(this), 64);
        }
    }

    public float getYaw()
    {
        return this.yaw;
    }

    public void rotatePitch(float by)
    {
        this.pitch += by;
        if (!this.worldObj.isRemote)
        {
            PacketHandler.instance().sendPacketToClients(this.getDescriptionPacket(), this.worldObj, new Vector3(this), 64);
        }
    }

    @Override
    public boolean simplePacket(String id, ByteArrayDataInput dis, Player player)
    {
        try
        {
            if (!super.simplePacket(id, dis, player) && this.worldObj.isRemote)
            {
                if (id.equalsIgnoreCase("Desc"))
                {
                    this.functioning = dis.readBoolean();
                    this.yaw = dis.readFloat();
                    this.pitch = dis.readFloat();
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.instance().getTilePacket(this.getChannel(), this, "Desc", this.functioning, this.yaw, this.pitch);
    }

    public void fireLaser()
    {

        Vector3 start = RayTraceHelper.getPosFromRotation(this.worldObj, new Vector3(this).translate(new Vector3(0.5, 0.7, 0.5)), .7, yaw, pitch);
        MovingObjectPosition hitPos = RayTraceHelper.ray_trace_do(this.worldObj, start.toVec3(), yaw, pitch, range, false);
        Vector3 hitSpot = RayTraceHelper.getPosFromRotation(this.worldObj, start, range, yaw, pitch);
        
        if (hitPos != null)
        {
            LaserEvent event = new LaserEvent.LaserFireEvent(this, hitPos);
            MinecraftForge.EVENT_BUS.post(event);

            if (!worldObj.isRemote && !event.isCanceled() && this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
            {
                if (hitPos.typeOfHit == EnumMovingObjectType.ENTITY && hitPos.entityHit != null)
                {
                    System.out.println("Entity hit by laser");
                    DamageSource damageSource = TileDamageSource.doLaserDamage(this);
                    hitPos.entityHit.attackEntityFrom(damageSource, 7);
                    hitPos.entityHit.setFire(8);
                }
                else if (hitPos.typeOfHit == EnumMovingObjectType.TILE)
                {
                    if (this.hit != null && this.hit.equals(new Vector3(hitPos)) && !this.hit.equals(new Vector3(this)))
                    {
                        this.hitTicks++;

                        if (hitTicks >= 6)
                        {
                            LaserEvent.onBlockMinedByLaser(this.worldObj, this, this.hit);
                            this.hit = null;
                            this.hitTicks = 0;
                        }
                    }
                    else
                    {
                        this.hitTicks = 1;
                        this.hit = new Vector3(hitPos);
                        LaserEvent.onLaserHitBlock(this.worldObj, this, this.hit, ForgeDirection.UP);
                    }

                }

            }
            hitSpot = new Vector3(hitPos.hitVec);
        }
        DarkMain.proxy.renderBeam(this.worldObj, start, hitSpot, this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) ? Color.ORANGE : Color.blue, 3);
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
