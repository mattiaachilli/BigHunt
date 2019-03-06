package model.gun;

import javafx.scene.shape.Shape;
import model.entities.AbstractEntity;
import model.properties.Velocity;

public class BulletImpl extends AbstractEntity implements Bullet {
    
    private final double damage;
    private BulletType bulletType;
    
    public BulletImpl(Shape shape, Velocity velocity, double damage, BulletType bulletType) {
        super(shape, velocity);
        this.damage = damage;
        this.bulletType = bulletType;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public void setBulletType(BulletType bulletType) {
        this.bulletType = bulletType;
    }

    @Override
    public BulletType getBulletType() {
        return this.bulletType;
    }

}
