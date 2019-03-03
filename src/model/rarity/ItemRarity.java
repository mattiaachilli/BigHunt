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
	
    VERY_COMMON(80),
    COMMON(60),
    RARE(40),
    VERY_RARE(5);
	
    private final int value;
	
    private ItemRarity(final int newValue) {
	this.value = newValue;
    }
	
    public int getValue() {
	return this.value;
    }
}
