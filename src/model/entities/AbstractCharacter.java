package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Velocity;
import model.utilities.ExceptionRuntimeUtil;

/**
 * 
 * Abstract character of the entity.
 */
public class AbstractCharacter extends AbstractEntity implements Character {

    private int timeElapsed;
    private EntityStatus status;

    /**
     * Abstract Character constructor.
     * 
     * @param shape
     *          entity's shape
     * @param velocity
     *          entity's velocity
     */
    public AbstractCharacter(final Shape shape, final Velocity velocity) {
        super(shape, velocity);
        this.timeElapsed = 0;
        this.status = EntityStatus.ALIVE;
    }

    /**
     * Kill the entity(duck, dog cannot be killed).
     */
    @Override
    public void kill() {
        ExceptionRuntimeUtil.checkException(!this.isAlive(), new IllegalStateException());
        this.status = EntityStatus.DEAD;
    }

    @Override
    public final boolean isAlive() {
        return this.status == EntityStatus.ALIVE;
    }

    /**
     * Change the entity's status.
     * 
     * @param status
     *          to set
     */
    @Override
    public void setStatus(final EntityStatus status) {
        ExceptionRuntimeUtil.checkException(!this.isAlive(), new IllegalStateException());
        this.status = status;
    }

    @Override
    public final EntityStatus getStatus() {
        return this.status;
    }

    @Override
    public final int getTimeElapsed() {
        return this.timeElapsed;
    }

    @Override
    public final void addTimeElapsed(final int timeElapsed) {
        this.timeElapsed += timeElapsed;
    }

    @Override
    public final void resetTimeElapsed() {
        this.timeElapsed = 0;
    }
}
