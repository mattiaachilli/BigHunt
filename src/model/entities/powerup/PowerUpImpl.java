package model.entities.powerup;

import javafx.scene.shape.Shape;
import model.ModelImpl;
import model.entities.AbstractEntity;
import model.properties.Velocity;
import model.properties.VelocityImpl;

/**
 * 
 * This class represents a power up.
 *
 */
public class PowerUpImpl extends AbstractEntity implements PowerUp {

    private static final Velocity POWER_UP_VELOCITY = new VelocityImpl(0.0, ModelImpl.GAME_HEIGHT / 5);
    private final PowerUpType type;
    private boolean visible;
    private boolean hit;

    /**
     * Constructor of the power up.
     * 
     * @param type
     *          power up type
     * @param shape
     *          of the power up
     * @param velocity
     *          velocity of power up
     */
    public PowerUpImpl(final PowerUpType type, final Shape shape, final Velocity velocity) {
        super(shape, velocity);
        this.type = type;
        this.visible = false;
        this.hit = false;
    }

    @Override
    public final PowerUpType getType() {
        return this.type;
    }

    @Override
    public final void setVisible() {
        this.visible = true;
    }

    @Override
    public final boolean isVisible() {
        return this.visible;
    }

    @Override
    public final void update(final int timeElapsed) { 
        if (this.visible && !this.hit) {
            this.setVelocity(POWER_UP_VELOCITY);
            super.update(timeElapsed);
        }
    }

    @Override
    public final boolean isHit() {
        return this.hit;
    }

    @Override
    public final void hit() {
        this.hit = true;
    }
}
