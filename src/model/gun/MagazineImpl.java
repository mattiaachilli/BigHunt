package model.gun;

/**
 * 
 * Implementation of Magazine interface.
 *
 */
public class MagazineImpl implements Magazine {

    private static final int MAX_AMMO = 8;

    private int magNumber;
    private int ammo;
    private BulletType bulletType;

    /**
     * MagazineImpl constructor.
     * 
     * @param magNumber represents the serial number of the magazine.
     */
    public MagazineImpl(final int magNumber) {
        this.ammo = MAX_AMMO;
        this.magNumber = magNumber;
        this.bulletType = BulletType.NORMAL_BULLET;
    }

    @Override
    public final void shoot() {
        if (this.getBulletType().equals(BulletType.NORMAL_BULLET)) {
            this.ammo--;
        }
    }

    @Override
    public final int getAmmo() {
        return this.ammo;
    }

    @Override
    public final int getNumber() {
        return this.magNumber;
    }

    @Override
    public final void setBulletType(final BulletType bulletType) {
        this.bulletType = bulletType;
    }

    @Override
    public final BulletType getBulletType() {
        return this.bulletType;
    }

}
