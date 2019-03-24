package model.gun;

import javafx.scene.shape.Shape;
import model.properties.Position;
import model.properties.Velocity;
import model.entities.AbstractEntity;
import model.entities.StandardDuck;
import model.entities.standardDuck

public class AimImpl extends AbstractEntity implements Aim {

    private static final int MAX_AMMO = 5;
    
    private int ammo;
    private boolean hasAmmo;
    
    public AimImpl(Shape shape, Velocity velocity) {
        super(shape, velocity);
        this.ammo = MAX_AMMO;
        this.hasAmmo = true;
    }
    
    public void shoot() {
        this.ammo --;
        if(this.getPosition() == StandardDuck.getPosition())
    }
    
    private boolean recharge() {
        this.ammo = MAX_AMMO;
        this.hasAmmo = true;
        return this.hasAmmo;
    }

    @Override
    public int getAmmo() {
        return this.ammo;
    }

    @Override
    public boolean canShoot() {
        return this.hasAmmo == true ? this.hasAmmo : this.recharge();
    }

    @Override
    public boolean hasPowerUp() {
        // TODO Auto-generated method stub
        return false;
    }

}
