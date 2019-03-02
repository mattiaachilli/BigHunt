package model.entities;

/**
 * 
 * Interface that represents the life of an entity
 *
 */
public interface Health extends Entity {
    
   /**
    * Kill this entity.
    */
    void kill();
    
    /**
     * Indicate if an entity is alive or died.
     * 
     * @return true if the entity is still alive.
     */
    boolean isAlive();
}
