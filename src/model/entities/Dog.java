package model.entities;

import java.util.Optional;

/**
 * 
 * Interface that represents the dog in the game, dog hasn't a health, score etc...
 *
 */
public interface Dog extends Character {

    /**
     * Check if dog is in grass.
     * 
     * @return true if dog is in grass.
     */
    boolean isInGrass();

    /**
     * Gets the current dog status.
     * 
     * @return dog's status.
     * 
     */
    DogStatus getDogStatus();

    /**
     * Sets a new dog status.
     * 
     * @param status
     *          of the dog
     */
    void setDogStatus(DogStatus status);

    /**
     * Sets the last duck killed.
     * 
     * @param duck
     *          last duck killed.
     */
    void setLastDuckKilled(Duck duck);

    /**
     * Gets the last duck killed if exist.
     * 
     * @return last duck killed.
     */
    Optional<Duck> getLastDuckKilled();

    /**
     * Gets negative score when hit dog.
     * 
     * @return the negative score for hitting the dog.
     */
    int getDogNegativeScore();
}
