package dark.mining.common.gas;

/**
 * @author Archadia
 *
 */
public class Gas {
	
	public int gasID;
	private GasTypes type;
	private String name;
	
	public Gas(int id, GasTypes gasType, String gasName) {
		type = gasType;
		name = gasName;
		gasID = id;
	}
	
	public String getName() {
		return name;
	}
}
