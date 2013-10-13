package dark.mining.common.machines.scanner;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import dark.core.prefab.machine.TileEntityEnergyMachine;
import dark.mining.TripleContainer;

/**
 * @author Archadia
 *
 */
public class TileEntityScanner extends TileEntityEnergyMachine {

	private ArrayList<Integer> validTarget = new ArrayList<Integer>();
	private TripleContainer<Integer, Integer, Integer> usedTarget = new TripleContainer<Integer, Integer, Integer>();
	
	public boolean enabled = true;
	private Random rand = new Random();
		
	public void updateEntity() {
		short tries = 0;
		if(!worldObj.isRemote) {
			while(/*this.getEnergyStored() > 5000 &&*/ enabled) {
				tries++;
				if(tries > 5) {
					return;
				}
	
				scanArea();
			}
		}
	}
	
	private void scanArea() {
		//setEnergyStored(getEnergyStored() - 100);
		
		int targetX = xCoord - 150;
		int targetZ = zCoord - 150;
		int targetY = rand.nextInt(15) + 5;

		targetX += (rand.nextInt(300));
		targetZ += (rand.nextInt(300));
		
		int targetId = worldObj.getBlockId(targetX, targetY, targetZ);
		
		if(!usedTarget.getAList().contains(targetX) && !usedTarget.getBList().contains(targetY) && !usedTarget.getCList().contains(targetZ)) {
			if(isTargetValid(targetId)) {
				printResult(targetId);
				//setEnergyStored(getEnergyStored() - 50);
				usedTarget.put(targetX, targetY, targetZ);
			}
		}
	}
	
	private boolean isTargetValid(int id) {
		for(Block block : Block.blocksList) {
			if(block.blockID == id) {
				if(block.getUnlocalizedName().contains("ore")) return true;
			}
			return false;
		}
		return false;
	}
	
	private void printResult(int id) {
		System.out.println("Found " + id);
	}
}
