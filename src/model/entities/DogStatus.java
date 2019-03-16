package model.entities;

/**
 * 
 * This enum represents the current status of the dog in all phases.
 *
 */
public enum DogStatus {
    /**
     * RIGHT.
     */
    RIGHT("Dog right"),
    /**
     * SNIFF.
     */
    SNIFF("Dog sniff the ground"),
    /**
     * ATTENTION.
     */
    ATTENTION("Dog attention"),
    /**
     * JUMP.
     */
    JUMP("Dog jump"),
    /**
     * LAUGH.
     */
    LAUGH("Dog laugh"),
    /**
     * EMPTY.
     */
    EMPTY("Empty");

    private final String description;

    DogStatus(final String description) {
        this.description = description;
    }

    /**
     * Get status.
     * 
     * @return the description of the status
     */
    public String getStatusDescription() {
        return this.description;
    }
}
