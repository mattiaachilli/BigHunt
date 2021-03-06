package model.entities;

/**
 * 
 * Represents the characters of the entities.
 *
 */
public interface Character extends Status {

    /**
     * Represents the time elapsed.
     * 
     * @return the time elapsed.
     */
    int getTimeElapsed();

    /**
     * Add the time elapsed of this entity.
     * 
     * @param timeElapsed 
     *          time to add
     */
    void addTimeElapsed(int timeElapsed);

    /**
     * Reset time elapsed.
     */
    void resetTimeElapsed();
}
