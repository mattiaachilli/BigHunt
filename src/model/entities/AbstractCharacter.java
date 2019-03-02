package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Velocity;

public class AbstractCharacter extends AbstractEntity implements Character {
    
    private int timeElapsed;
    private boolean alive;
    
    /**
     * @param shape
     * 		entity's shape
     * @param velocity
     * 		entity's velocity
     * @param life	
     * 		initial entity's life
     * @param maxLife
     * 		max entity's life
     */
    public AbstractCharacter(final Shape shape, final Velocity velocity) {
	super(shape, velocity);
	this.alive = true;
	this.timeElapsed = 0;
    }
    
    @Override
    public void kill() {
	this.alive = false;
    }

    @Override
    public boolean isAlive() {
	return this.alive;
    }

    @Override
    public int getTimeElapsed() {
	return this.timeElapsed;
    }

    @Override
    public void addTimeElapsed(int timeElapsed) {
	this.timeElapsed = timeElapsed; 
    }
}
