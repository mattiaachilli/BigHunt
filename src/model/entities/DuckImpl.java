package model.entities;

import java.util.Optional;

import javafx.scene.shape.Shape;
import model.properties.Velocity;

public class DuckImpl extends AbstractCharacter implements Duck {
    
    private static final int DEFAULT_SCORE = 100;
    
    private final Duck duck;
    private final Optional<PowerUp> powerUp;

    public DuckImpl(final Shape shape, final Velocity velocity) {
	super(shape, velocity);
    }

    @Override
    public Optional<PowerUp> getPowerUp() {
	return this.powerUp;
    }

    @Override
    public boolean hasPowerUp() {
	return false;
    }

    @Override
    public int getScore() {
	return DEFAULT_SCORE;
    }
}
