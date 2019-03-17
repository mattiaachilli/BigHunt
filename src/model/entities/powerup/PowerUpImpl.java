package model.entities.powerup;

import javafx.scene.shape.Shape;
import model.entities.AbstractEntity;
import model.properties.Velocity;

/**
 * 
 * This class represents a power up.
 *
 */
public class PowerUpImpl extends AbstractEntity implements PowerUp {

    private final PowerUpType type;

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
    }

    @Override
    public final PowerUpType getType() {
        return this.type;
    }
}
