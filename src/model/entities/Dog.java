package model.entities;

import java.util.Optional;

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

    /**
     * Set last duck killed.
     * 
     * @param duck
     *          last duck killed.
     */
    void setLastDuckKilled(Duck duck);

    /**
     * Get last duck killed if exist.
     * @return last duck killed.
     */
    Optional<Duck> getLastDuckKilled();

    /**
     * 
     * @return the negative score for hitting the dog.
     */
    int getDogNegativeScore();
}
