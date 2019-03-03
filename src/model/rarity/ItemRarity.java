package model.rarity;

/**
 * 
 * Enumeration used to associate the rarity whit its values
 *
 */
public enum ItemRarity {
	
    /**
    * Type of rarity
    */
    VERY_COMMON(40),
    COMMON(30),
    RARE(20),
    VERY_RARE(10);
	
    private final int value;
	
    private ItemRarity(final int newValue) {
	this.value = newValue;
    }
	
    public int getValue() {
	return this.value;
    }
}
