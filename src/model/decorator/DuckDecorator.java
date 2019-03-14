package model.decorator;

import java.awt.Graphics2D;
import java.util.Optional;

import javafx.scene.shape.Shape;
import model.entities.Duck;
import model.entities.EntityStatus;
import model.entities.powerup.PowerUp;
import model.properties.DuckDirection;
import model.properties.Position;
import model.properties.Velocity;

/**
 * 
 * Duck decorator for various type of duck.
 *
 */

public abstract class DuckDecorator implements Duck {
    
    private final Duck duck;
    
    public DuckDecorator(final Duck duck) {
	this.duck = duck;
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
	this.duck.update(timeElapsed);
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
    public void kill() {
	this.duck.kill();
    }
    
    @Override
    public void computeFlyAway() {
        this.duck.computeFlyAway();
    }

    @Override
    public void setStatus(EntityStatus status) {
        this.duck.setStatus(status);
    }

    @Override
    public EntityStatus getStatus() {
        return this.duck.getStatus();
    }

    @Override
    public int getScore() {
	return (this.duck.getScore() * this.getScoreMultiplier());
    }
    
    @Override
    public DuckDirection getActualDirection() {
        return this.duck.getActualDirection();
    }
    
    public void setDirection(final DuckDirection direction) {
        this.duck.setDirection(direction);
    }
   
    @Override
    public boolean canFlyAway() {
        return this.duck.canFlyAway();
    }
    
    @Override
    public void setMovementChange(boolean change) {
        this.duck.setMovementChange(change);
    }
    
    public abstract int getScoreMultiplier();
    
    //DA TOGLIERE POI
    /*
    public void render(Graphics2D g) {
        this.duck.render(g);
    }
    */
}
