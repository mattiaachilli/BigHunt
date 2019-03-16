package model.entities;

/**
 * 
 * Interface that represents the dog in the game, dog hasn't a health, score etc...
 *
 */
public interface Dog extends Entity {

    /**
     * Check if dog is in grass.
     * 
     * @return true if dog is in grass.
     */
    boolean isInGrass();

    /**
     * Dog is in grass.
     */
    void inGrass();

    /**
     * Get the actual dog status.
     * 
     * @return dog's status.
     * 
     */
    DogStatus getDogStatus();
}
