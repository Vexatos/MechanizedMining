package dark.mining.common.gas;

/**
 * @author Archadia
 *
 */
public enum GasTypes {
	METHANE("Methane", 5.0f),
	BUTANE("Butane", 4.46F),
	PROPANE("Propane", 3.32f),
	ETHANE("Ethane", 2.2f),
	HELIUM("Helium", 1.546f),
	NITROGEN("Nitrogen", 1.943f),
	CARBONDIOXIDE("CO2", 4.783f);
	
	/* 1 = Rare, 10 = Extremely Common */
	private float gasRarity;
	private String gasName;
	
	GasTypes(String name, float rarity) {
		gasName = name;
		gasRarity = rarity;
	}
	
	/** Rarity affects the chances of getting the gas when refining */
	public int getRarity() {
		return Math.round(((gasRarity * 10) * 2));
	}
	
	public String getFriendlyName() {
		return gasName;
	}
	
	public String getCodeName() {
		return "gas_" + gasName.toLowerCase();
	}
}
