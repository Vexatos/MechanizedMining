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
        Vector3 start = RayTraceHelper.getPosFromRotation(new Vector3(this), 1.5, yaw, pitch);
        MovingObjectPosition hit = RayTraceHelper.ray_trace_do(this.worldObj, start.toVec3(), yaw, pitch, distacne, false);
        Vector3 hitSpot = RayTraceHelper.getPosFromRotation(new Vector3(this), distacne, yaw, pitch);
        //TODO fix sound
        if (hit != null)
        {
            LaserEvent event = new LaserEvent.LaserFireEvent(this, hit);
            MinecraftForge.EVENT_BUS.post(event);

            if (!worldObj.isRemote && !event.isCanceled())
            {
                if (hit.typeOfHit == EnumMovingObjectType.ENTITY && hit.entityHit != null)
                {
                    DamageSource damageSource = TileDamageSource.doLaserDamage(this);
                    hit.entityHit.attackEntityFrom(damageSource, 7);
                    hit.entityHit.setFire(5);
                }
                else if (hit.typeOfHit == EnumMovingObjectType.TILE)
                {
                    if (hit != null && hit.equals(new Vector3(hit.blockX, hit.blockY, hit.blockZ)))
                    {
                        this.hitTicks++;
                        if (hitTicks >= 5)
                        {

                            this.hit.setBlock(this.worldObj, 0);
                        }
                    }
                    else
                    {
                        this.hit = new Vector3(hit.blockX, hit.blockY, hit.blockZ);
                    }

                }

            }
            hitSpot = new Vector3(hit.hitVec);
        }
        //TODO make beam brighter the longer it has been used
        //TODO adjust the laser for the end of the gun
        DarkMain.getInstance();
        DarkMain.proxy.renderBeam(this.worldObj, start, hitSpot, Color.ORANGE, 1);
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

    @Override
    public boolean simplePacket(String id, ByteArrayDataInput dis, Player player)
    {
        try
        {
            if (this.worldObj.isRemote)
            {
                if (id.equalsIgnoreCase(SimplePacketTypes.RUNNING.name))
                {
                    this.functioning = dis.readBoolean();
                    return true;
                }
                if (id.equalsIgnoreCase(SimplePacketTypes.NBT.name))
                {
                    this.readFromNBT(Packet.readNBTTagCompound(dis));
                    return true;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.instance().getPacket(this.getChannel(), this, SimplePacketTypes.RUNNING.name, this.functioning, this.getTarget());
    }

    /** Sends a simple true/false am running power update */
    public void sendPowerUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            PacketHandler.instance().sendPacketToClients(PacketHandler.instance().getPacket(this.getChannel(), this, SimplePacketTypes.RUNNING.name, this.functioning, this.getTarget()), worldObj, new Vector3(this), 64);
        }
    }
}
