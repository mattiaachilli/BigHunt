package model.entities;

/**
 * 
 * Interface that represents the status of an entity.
 *
 */
public interface Status extends Entity {

    /**
     * Indicates if an entity is alive or dead.
     * 
     * @return true if the entity is alive.
     */
    boolean isAlive();

   /**
    * Kills this entity.
    * 
    */
    void kill();

    /**
     * Changes the entity's status.
     * 
     * @param status
     *          to set
     */
    void setStatus(EntityStatus status);

    /**
     * Gets the current entity's status.
     * 
     * @return current entity's status.
     */
    EntityStatus getStatus();
}
