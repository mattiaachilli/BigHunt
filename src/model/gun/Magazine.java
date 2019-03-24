package model.gun;

import model.entities.Entity;

/**
 * 
 * Interface that represents the gun's scope, with which you can aim to shoot.
 *
 */
public interface Aim extends Entity {
    
    /**
     * 
     * @return the number of bullets.
     */
    int getAmmo();
    
    /**
     * 
     * @return false if the gun is reloading.
     */
    boolean canShoot();
    
    /**
     * 
     * @return true if you have picked up a PowerUp.
     */
    boolean hasPowerUp();

}
