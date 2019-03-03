package model.entities.powerup;

import javafx.scene.shape.Shape;
import model.entities.AbstractEntity;
import model.properties.Velocity;

public class PowerUpImpl extends AbstractEntity implements PowerUp {

    private final PowerUpType type;
    
    public PowerUpImpl(final PowerUpType type, final Shape shape, final Velocity velocity) {
	super(shape, velocity);
	this.type = type;
    }

    @Override
    public PowerUpType getType() {
	return this.type;
    }
}
