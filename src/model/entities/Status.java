package model.entities;

/**
 * 
 * Interface that represents the status of an entity.
 *
 */
public interface Status extends Entity {

    /**
     * Indicate if an entity is alive or died.
     * 
     * @return true if the entity is alive.
     */
    boolean isAlive();

   /**
    * Kill this entity.
    * 
    */
    void kill();

    /**
     * Change the entity's status.
     * 
     * @param status
     *          to set
     */
    void setStatus(EntityStatus status);

    /**
     * Get the current entity's status.
     * 
     * @return current entity's status.
     */
    EntityStatus getStatus();
}
