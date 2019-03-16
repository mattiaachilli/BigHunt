package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Velocity;
import model.utilities.ExceptionRuntimeUtility;

/**
 * 
 * Abstract character of the entity.
 *
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

    @Override
    public void kill() {
        ExceptionRuntimeUtility.checkException(!this.isAlive(), new IllegalStateException());
        this.status = EntityStatus.DEAD;
    }

    @Override
    public final boolean isAlive() {
        return this.status == EntityStatus.ALIVE;
    }

    @Override
    public final void setStatus(final EntityStatus status) {
        ExceptionRuntimeUtility.checkException(!this.isAlive(), new IllegalStateException());
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
}
