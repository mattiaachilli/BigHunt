package model.entities;

/**
 * 
 * Interface that represents the life of an entity
 *
 */
public interface Health extends Entity {
    
    /**
     * Get the health of this entity.
     * 
     * @return entity's health
     */
    int getHealth();
    
    /**
     * Get the max health of this entity.
     * 
     * @return max entity's health
     */
    int getMaxHealth();
    
    /**
     * Decrease health of this entity.
     * 
     * @param health to decrease.
     */
    void decreaseHealt(int health);
    
    /**
     * Indicate if an entity is alive or died.
     * 
     * @return true if the entity is still alive.
     */
    boolean isAlive();
}
