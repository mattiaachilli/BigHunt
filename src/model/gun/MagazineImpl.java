package model.gun;

/**
 * 
 * Implementation of Magazine interface.
 *
 */
public class MagazineImpl implements Magazine {

    private static final int MAX_AMMO = 5;
    private static final int MAX_TIME = 5000;

    private long powerUpTime;
    private int magNumber;
    private int ammo;
    private boolean hasAmmo;
    private BulletType bulletType;

    /**
     * MagazineImpl constructor.
     * 
     * @param magNumber represents the serial number of the magazine.
     */
    public MagazineImpl(final int magNumber) {
        this.ammo = MAX_AMMO;
        this.hasAmmo = true;
        this.magNumber = magNumber;
        this.bulletType = BulletType.NORMAL_BULLET;
    }

    @Override
    public void shoot() {
        if (this.getBulletType().equals(BulletType.NORMAL_BULLET)) {
            this.ammo--;
        }
    }

    @Override
    public int getAmmo() {
        return this.ammo;
    }

    @Override
    public int getNumber() {
        return this.magNumber;
    }

    @Override
    public void setBulletType(BulletType bulletType) {
        this.bulletType = bulletType;
        if (this.bulletType.equals(BulletType.INFINITE_BULLETS)) {
            this.powerUpTime = System.currentTimeMillis();
        }
    }

    @Override
    public BulletType getBulletType() {
        return this.bulletType;
    }

    @Override
    public void update(final int timeElapsed) {
        if (this.bulletType.equals(BulletType.INFINITE_BULLETS)) {
            this.powerUpTime = System.currentTimeMillis() - this.powerUpTime;
            if (this.powerUpTime > MAX_TIME) {
                this.setBulletType(BulletType.NORMAL_BULLET);
            }
        }
    }

}
