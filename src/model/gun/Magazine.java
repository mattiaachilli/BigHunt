package model.gun;

/**
 * 
 * Interface that represents a magazine of a gun, with bullets and a serial number.
 *
 */
public interface Magazine {

    /**
     * 
     * Decrements ammo.
     */
    void shoot();
    /**
     * 
     * @return the number of bullets.
     */
    int getAmmo();

    /**
     * 
     * @return the serial number of the magazine.
     */
    int getNumber();

    /**
     * 
     * @param bulletType is used to set the bullet type.
     */
    void setBulletType(BulletType bulletType);

    /**
     * 
     * @return the bullet type
     */
    BulletType getBulletType();

}
