package dark.mining.item.tool;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import universalelectricity.core.vector.Vector3;

import com.builtbroken.common.Triple;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.core.common.DarkMain;
import dark.core.interfaces.IExtraInfo.IExtraItemInfo;
import dark.core.prefab.helpers.RayTraceHelper;
import dark.mining.MechanizedMining;

/** Stream laser mining tool, When held down it will slowly mine away at the block in front of it.
 *
 *
 * TODO create model for this that is 3D. The front should spin around the barrel as its mines
 * generating a laser. As well the player should be wearing a battery pack when the laser is out.
 * Other option is to force the player to wear a battery pack as armor when using the tool
 *
 * @author DarkGuardsman */
public class ItemMiningLaser extends ItemElectricTool implements IExtraItemInfo
{
    float batterySize = 100;
    float wattPerShot = 1;
    float damageToEntities = 1.3f;
    int blockRange = 50;
    int firingDelay = 5;
    int breakTime = 15;

    HashMap<EntityPlayer, Triple<World, Vector3, Integer>> miningMap = new HashMap<EntityPlayer, Triple<World, Vector3, Integer>>();

    public ItemMiningLaser()
    {
        super("MiningLaser", true);
        this.setMaxStackSize(1);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        //TODO change render of the laser too show it slowly over heat, when it over heats eg gets to max use damage the player, and tool
        return 1000;
    }

    @Override
    public void onCreated(ItemStack stack, World par2World, EntityPlayer entityPlayer)
    {
        this.setElectricity(stack, 0);
        if (stack.getTagCompound() == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }
        if (entityPlayer != null)
        {
            stack.getTagCompound().setString("Creator", entityPlayer.username);
        }
    }

    @Override
    public void onUsingItemTick(ItemStack stack, EntityPlayer entityLiving, int count)
    {
        if (count > 5)
        {
            Vec3 playerPosition = Vec3.createVectorHelper(entityLiving.posX, entityLiving.posY + entityLiving.getEyeHeight(), entityLiving.posZ);
            Vec3 playerLook = RayTraceHelper.getLook(entityLiving, 1.0f);
            Vec3 p = Vec3.createVectorHelper(playerPosition.xCoord + playerLook.xCoord * 2, playerPosition.yCoord + playerLook.yCoord * 2, playerPosition.zCoord + playerLook.zCoord * 2);

            Vec3 playerViewOffset = Vec3.createVectorHelper(playerPosition.xCoord + playerLook.xCoord * blockRange, playerPosition.yCoord + playerLook.yCoord * blockRange, playerPosition.zCoord + playerLook.zCoord * blockRange);

            MovingObjectPosition hit = RayTraceHelper.ray_trace_do(entityLiving.worldObj, entityLiving, new Vector3().toVec3(), blockRange, true);
            entityLiving.worldObj.playSound(entityLiving.posX, entityLiving.posY, entityLiving.posZ, MechanizedMining.instance.PREFIX + "laserHum", 0.5f, 0.7f, true);
            Vec3 lookVec = entityLiving.getLookVec();
            if (hit != null)
            {
                if (hit.typeOfHit == EnumMovingObjectType.ENTITY && hit.entityHit != null)
                {
                    DamageSource damageSource = DamageSource.causePlayerDamage((EntityPlayer) entityLiving);
                    hit.entityHit.attackEntityFrom(damageSource, damageToEntities);
                }
                else if (hit.typeOfHit == EnumMovingObjectType.TILE)
                {
                    int time = 0;
                    boolean mined = false;
                    if (miningMap.containsKey(entityLiving))
                    {
                        Triple<World, Vector3, Integer> lastHit = miningMap.get(entityLiving);
                        if (lastHit != null && lastHit.getA() == entityLiving.worldObj && lastHit.getB() != null && lastHit.getB().equals(new Vector3(hit.hitVec)))
                        {
                            time = lastHit.getC() + 1;
                            if (time >= breakTime)
                            {
                                lastHit.getB().setBlock(entityLiving.worldObj, 0);
                                mined = true;
                            }
                        }
                    }
                    if (!mined)
                    {
                        miningMap.put(entityLiving, new Triple(entityLiving.worldObj, new Vector3(hit.hitVec), time));
                    }

                }
                playerViewOffset = hit.hitVec;
            }
            DarkMain.getInstance();
            DarkMain.proxy.renderBeam(entityLiving.worldObj, new Vector3(p).translate(new Vector3(0, -.4, 0)), new Vector3(playerViewOffset), Color.RED, 1);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        super.addInformation(stack, player, list, par4);
        if (stack.getTagCompound() == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }
        String creator = stack.getTagCompound().getString("Creator");
        if (!creator.equalsIgnoreCase("creative") && creator != "")
        {
            list.add("Created by: " + creator);
        }
        else if (creator.equalsIgnoreCase("creative"))
        {
            list.add("Created by Magic Dwarfs");
        }

    }

    @Override
    public float getMaxElectricityStored(ItemStack theItem)
    {
        return this.batterySize;
    }

    @Override
    public boolean hasExtraConfigs()
    {
        return true;
    }

    @Override
    public void loadExtraConfigs(Configuration config)
    {
        this.blockRange = config.get("Laser", "Range", this.blockRange).getInt(this.blockRange);
        this.firingDelay = config.get("Laser", "Delay", this.firingDelay).getInt(this.firingDelay);
        this.damageToEntities = (float) config.get("Laser", "Damage", this.damageToEntities).getDouble(this.damageToEntities);
        this.batterySize = (float) (config.get("Energy", "BatteryCap", this.batterySize * 1000).getDouble(this.batterySize * 1000) / 1000);
        this.wattPerShot = (float) (config.get("Energy", "FiringCost", this.wattPerShot * 1000).getDouble(this.wattPerShot * 1000) / 1000);

    }

    @Override
    public void loadOreNames()
    {
        OreDictionary.registerOre("MiningLaserGun", this);

    }

}
