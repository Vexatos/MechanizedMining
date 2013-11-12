package dark.mining.machines.tile;

import net.minecraft.block.Block;
import universalelectricity.core.vector.Vector3;
import dark.core.common.blocks.BlockGasOre;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.MMRecipeLoader;
import dark.mining.MechanizedMining;

/** @author Archadia */
public class TileFracker extends TileEntityEnergyMachine {

	private Vector3[] clearingTargets = new Vector3[9];
	private Vector3[] pipeTargets = new Vector3[9];

	public static final float STAGE_1_USAGE = 0.5f;
	public static final float STAGE_2_USAGE = 0.5f;
	public static final float STAGE_3_USAGE = 2.0f;
	public static final float STAGE_4_USAGE = 4.0f;

	public TileFracker() {
		super(0, 5);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			if (this.ticks % 20 == 0) {
				collect();
			}
		}
	}

	/** Returns if its finished its job */
	public void clearArea() {
		if (clearingTargets == null) {
			clearingTargets = new Vector3(xCoord, yCoord, zCoord);
		}
		if (clearingTargets.intY() > 0) {
			if (this.getEnergyStored() >= STAGE_1_USAGE) {
				clearingTargets.translate(Vector3.DOWN());

				int blockID = clearingTargets.getBlockID(this.worldObj);
				Block block = Block.blocksList[blockID];
				if (block != null
						&& !block.isAirBlock(this.worldObj, clearingTargets.intX(),
								clearingTargets.intY(), clearingTargets.intZ())
						&& block.getBlockHardness(this.worldObj, clearingTargets.intX(),
								clearingTargets.intY(), clearingTargets.intZ()) >= 0) {
					worldObj.setBlockToAir(clearingTargets.intX(), clearingTargets.intY(),
							clearingTargets.intZ());
					this.consumePower(STAGE_1_USAGE, true);
				}
			}
		}
	}

	public void collect() {
		if (pipeTargets == null) {
			pipeTargets = new Vector3(xCoord, yCoord, zCoord);
		}
		if (pipeTargets.intY() > 0) {
			if (this.getEnergyStored() >= STAGE_2_USAGE) {
				pipeTargets.translate(Vector3.DOWN());

				int blockID = pipeTargets.getBlockID(this.worldObj);
				Block block = Block.blocksList[blockID];
				if (block == null) {
					worldObj.setBlock(pipeTargets.intX(), pipeTargets.intY(),
							pipeTargets.intZ(), MMRecipeLoader.frackingPipe.blockID);
					this.consumePower(STAGE_2_USAGE, true);
				}
			}
		}
	}
}