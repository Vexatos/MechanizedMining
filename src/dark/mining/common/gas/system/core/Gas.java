package dark.mining.common.gas.system.core;


/**
 * @author Archadia
 *
 */
public class Gas {

	public int gasID;
		
	private String gasName;
	
	private float density;
	
	public static Gas[] gasList = new Gas[256];
			
	public Gas(int id, float density, String type) {
		this.gasID = id;
		this.gasName = type;
		this.density = density;
		
		gasList[id] = this;
	}
	
	public float getDensity() {
		return density;
	}
	
	public String getName() {
		if(gasName == null) {
			return "Null";
		}
		return gasName;
	}
}
