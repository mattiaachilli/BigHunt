package model.decorator;

import java.util.Optional;

import javafx.scene.shape.Shape;
import model.conversions.TimeConversion;
import model.entities.Duck;
import model.entities.powerup.PowerUp;
import model.properties.Position;
import model.properties.Velocity;

/**
 * 
 * Duck decorator for various type of duck.
 *
 */

public abstract class DuckDecorator implements Duck {
    
    private final Duck duck;
    private int lastVelocityUpdate;
    
    public DuckDecorator(final Duck duck) {
	this.duck = duck;
	this.lastVelocityUpdate = 0;
    }
    
    @Override
    public int getTimeElapsed() {
	return this.duck.getTimeElapsed();
    }

    @Override
    public void addTimeElapsed(final int timeElapsed) {
	this.duck.addTimeElapsed(timeElapsed);
    }

    @Override
    public boolean isAlive() {
	return this.duck.isAlive();
    }

    @Override
    public Position getPosition() {
	return this.duck.getPosition();
    }

    @Override
    public void setPosition(final Position position) {
	this.duck.setPosition(position);
    }

    @Override
    public Velocity getVelocity() {
	return this.duck.getVelocity();
    }

    @Override
    public void setVelocity(final Velocity velocity) {
	this.duck.setVelocity(velocity);
    }

    @Override
    public Shape getShape() {
	return this.duck.getShape();
    }

    @Override
    public void setShape(final Shape shape) {
	this.duck.setShape(shape);
    }

    @Override
    public void update(final int timeElapsed) {
	this.lastVelocityUpdate += this.getTimeElapsed();
	if(this.canUpdateVelocity()) {
	    this.setNewVelocity();
	    this.lastVelocityUpdate -= 1; 
	}
	this.duck.update(timeElapsed);
    }
    
    private boolean canUpdateVelocity() {
	return TimeConversion.getSecondsByMillis(this.lastVelocityUpdate) >= 1;
    }
    
    @Override
    public Optional<PowerUp> getPowerUp() {
	return this.duck.getPowerUp();
    }

    @Override
    public boolean hasPowerUp() {
	return this.duck.hasPowerUp();
    }
    
    @Override
    public long getTimeFromBirth() {
	return this.duck.getTimeFromBirth();
    }

    @Override
    public boolean canFlyAway() {
	return this.duck.canFlyAway();
    }

    @Override
    public void kill() {
	this.duck.kill();
    }

    @Override
    public int getScore() {
	return (this.duck.getScore() * this.getScoreMultiplier());
    }
    
    public abstract int getScoreMultiplier();
    
    public abstract void setNewVelocity();
}
