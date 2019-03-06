package model.gun;

import model.entities.Entity;

/**
 * 
 * Interface that represents the bullets of the gun.
 *
 */

public interface Bullet extends Entity {
    
    /**
     * 
     * @return a double representing the damage of a single bullet.
     */
    double getDamage();
    
    /**
     * Sets the BulletType.
     */
    void setBulletType(BulletType bulletType);
    
    /**
     * 
     * @return the BulletType being used.
     */
    BulletType getBulletType();
}
