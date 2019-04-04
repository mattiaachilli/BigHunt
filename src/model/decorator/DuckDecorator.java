package model.decorator;

import java.util.Optional;

import javafx.scene.shape.Shape;
import model.entities.Duck;
import model.entities.DuckProperty;
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

    /**
     * 
     * @param duck
     *          duck to decorate
     */
    public DuckDecorator(final Duck duck) {
        this.duck = duck;
    }

    @Override
    public final int getTimeElapsed() {
        return this.duck.getTimeElapsed();
    }

    @Override
    public final void addTimeElapsed(final int timeElapsed) {
        this.duck.addTimeElapsed(timeElapsed);
    }

    @Override
    public final boolean isAlive() {
        return this.duck.isAlive();
    }

    @Override
    public final Position getPosition() {
        return this.duck.getPosition();
    }

    @Override
    public final void setPosition(final Position position) {
        this.duck.setPosition(position);
    }

    @Override
    public final Velocity getVelocity() {
        return this.duck.getVelocity();
    }

    @Override
    public final void setVelocity(final Velocity velocity) {
        this.duck.setVelocity(velocity);
    }

    @Override
    public final Shape getShape() {
        return this.duck.getShape();
    }

    @Override
    public final void setShape(final Shape shape) {
        this.duck.setShape(shape);
    }

    @Override
    public final void update(final int timeElapsed) {
        this.duck.update(timeElapsed);
    }

    @Override
    public final Optional<PowerUp> getPowerUp() {
        return this.duck.getPowerUp();
    }

    @Override
    public final boolean hasPowerUp() {
        return this.duck.hasPowerUp();
    }

    @Override
    public final void kill() {
        this.duck.kill();
    }

    @Override
    public final void computeFlyAway() {
        this.duck.computeFlyAway();
    }

    @Override
    public final void setStatus(final EntityStatus status) {
        this.duck.setStatus(status);
    }

    @Override
    public final EntityStatus getStatus() {
        return this.duck.getStatus();
    }

    @Override
    public final int getScore() {
        return (this.duck.getScore() * this.getScoreMultiplier());
    }

    @Override
    public final DuckDirection getActualDirection() {
        return this.duck.getActualDirection();
    }

    @Override
    public final void setDirection(final DuckDirection direction) {
        this.duck.setDirection(direction);
    }

    @Override
    public final boolean canFlyAway() {
        return this.duck.canFlyAway();
    }

    @Override
    public final void setMovementChange(final boolean change) {
        this.duck.setMovementChange(change);
    }

    @Override
    public final void setDecelerate() {
        this.duck.setDecelerate();
    }

    @Override
    public final DuckProperty getProperty() {
        return this.duck.getProperty();
    }

    /**
     * Get score multiplier of the duck.
     * 
     * @return the score multiplier of the duck
     */
    public abstract int getScoreMultiplier();
}
