package model.rarity;

/**
 * 
 * Enumeration used to associate the rarity whit its values.
 *
 */
public enum ItemRarity {
    /**
    * Type of rarity.
    * 
    * VERY COMMON.
    */
    VERY_COMMON(40),
    /**
     * COMMON.
     */
    COMMON(30),
    /**
     * RARE.
     */
    RARE(20),
    /**
     * VERY RARE.
     */
    VERY_RARE(10);

    private final int value;

    ItemRarity(final int newValue) {
        this.value = newValue;
    }

    /**
     * 
     * @return the rarity value
     */
    public int getValue() {
        return this.value;
    }
}
