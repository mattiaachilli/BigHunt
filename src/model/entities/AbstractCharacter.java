package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Velocity;
import model.utilities.ExceptionRuntimeUtility;

public class AbstractCharacter extends AbstractEntity implements Character {
    
    private int timeElapsed;
    private EntityStatus status;
    
    /**
     * @param shape
     * 		entity's shape
     * @param velocity
     * 		entity's velocity
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
    public boolean isAlive() {
	return this.status == EntityStatus.ALIVE;
    }

    @Override
    public int getTimeElapsed() {
	return this.timeElapsed;
    }

    @Override
    public void addTimeElapsed(final int timeElapsed) {
	this.timeElapsed = timeElapsed; 
    }
    
    @Override
    public void setStatus(final EntityStatus status) {
        ExceptionRuntimeUtility.checkException(!this.isAlive(), new IllegalStateException());
        this.status = status;
    }

    @Override
    public EntityStatus getStatus() {
        return this.status;
    }
}
