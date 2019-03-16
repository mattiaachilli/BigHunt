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
     * Get the actual dog status.
     * 
     * @return dog's status.
     * 
     */
    DogStatus getDogStatus();

    /**
     * Set new dog status.
     * 
     * @param status
     *          of the dog
     */
    void setDogStatus(DogStatus status);
}
