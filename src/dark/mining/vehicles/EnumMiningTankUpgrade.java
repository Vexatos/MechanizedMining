package dark.mining.vehicles;

/** Design sets for the mining tank to be created by the user
 * 
 * @author DarkGuardsman */
public enum EnumMiningTankUpgrade
{
    /** DRILL arm and cab */
    DRILL(),
    /** Small laser cannon and cab */
    LASER(),
    /** Large laser cannon and cab */
    HEAVY_LASER(),
    /** Robotic arm with shovel claw for digging with */
    DIGGER(),
    /** Robotic arm with claws at end for ripping up buildings */
    CLAW(),
    /** Robotic arm with saw at the end for cutting down trees */
    SAW(),
    /** Combat upgrade for the tank with a large flame thrower, only unlocked when GSM is installed */
    FIRE(),
    /** TNT cannon for tank that can be used for mining or combat */
    CANNON(),
    /** Catapult like sling design for the tank that can be used for anything */
    SLING();

}
