package dark.mining.machines.tile;

import net.minecraft.block.Block;
import universalelectricity.core.vector.Vector3;
import dark.core.common.blocks.BlockGasOre;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.MMRecipeLoader;
import dark.mining.MechanizedMining;

/** @author Archadia */
public class TileFracker extends TileEntityEnergyMachine {

	private Vector3[] clearingTarget = new Vector3[9];
	private Vector3[] pipeTarget = new Vector3[9];

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
		if (clearingTarget[0] == null) {
			clearingTarget[0] = new Vector3(xCoord, yCoord, zCoord);
		}
		if (clearingTarget[0].intY() > 0) {
			if (this.getEnergyStored() >= STAGE_1_USAGE) {
				clearingTarget[0].translate(Vector3.DOWN());

				int blockID = clearingTarget[0].getBlockID(this.worldObj);
				Block block = Block.blocksList[blockID];
				if (block != null
						&& !block.isAirBlock(this.worldObj, clearingTarget[0].intX(),
								clearingTarget[0].intY(), clearingTarget[0].intZ())
						&& block.getBlockHardness(this.worldObj, clearingTarget[0].intX(),
								clearingTarget[0].intY(), clearingTarget[0].intZ()) >= 0) {
					worldObj.setBlockToAir(clearingTarget[0].intX(), clearingTarget[0].intY(),
							clearingTarget[0].intZ());
					this.consumePower(STAGE_1_USAGE, true);
				}
			}
		}
	}

	public void collect() {
		if (pipeTarget[0] == null) {
			pipeTarget[0] = new Vector3(xCoord, yCoord, zCoord);
		}
		if (pipeTarget[0].intY() > 0) {
			if (this.getEnergyStored() >= STAGE_2_USAGE) {
				pipeTarget[0].translate(Vector3.DOWN());

				int blockID = pipeTarget[0].getBlockID(this.worldObj);
				Block block = Block.blocksList[blockID];
				if (block == null) {
					worldObj.setBlock(pipeTarget[0].intX(), pipeTarget[0].intY(),
							pipeTarget[0].intZ(), MMRecipeLoader.frackingPipe.blockID);
					this.consumePower(STAGE_2_USAGE, true);
				}
			}
		}
	}
}