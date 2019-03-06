package model.entities;

import java.util.Optional;

import javafx.scene.shape.Shape;
import model.entities.powerup.PowerUp;
import model.entities.utilities.EntityUtilities;
import model.properties.Velocity;
import model.utilities.ExceptionRuntimeUtility;

/**
 * 
 * This class represents a standard duck.
 *
 */

public class StandardDuck extends AbstractCharacter implements Duck {
    
    private static final int DEFAULT_SCORE = 50;
    private static final int TIME_FOR_PENALTY_SCORE = 5000;
    private static final int PENALTY_SCORE_FOR_FIVE_SECOND = 5;
    
    private final long initTime; //Duck's creation time
    private long dieTime; //Duck's died time
    private Optional<PowerUp> powerUp;

    public StandardDuck(final Shape shape, final Velocity velocity) {
	super(shape, velocity);
	this.initTime = System.currentTimeMillis();
	this.powerUp = this.getRandomPowerUp();
    }
    
    private Optional<PowerUp> getRandomPowerUp() {
	return Optional.empty();
    }

    @Override
    public Optional<PowerUp> getPowerUp() {
	return this.powerUp;
    }

    @Override
    public boolean hasPowerUp() {
	return this.powerUp.isPresent();
    }
    
    @Override
    public void kill() {
        ExceptionRuntimeUtility.checkException(!this.isAlive(), new IllegalStateException());
	super.kill();
	this.dieTime = System.currentTimeMillis();
    }
    
    @Override
    public long getTimeFromBirth() {
	long time;
	if(this.isAlive()) {
	    time = System.currentTimeMillis() -
		    this.initTime;
	} else { //Died
	    time = this.dieTime - this.initTime;
	}
	return time;
    }

    @Override
    public int getScore() {
        ExceptionRuntimeUtility.checkException(this.isAlive(), new IllegalStateException());
	final int penaltyTime = (int) this.getTimeFromBirth() / TIME_FOR_PENALTY_SCORE;
	final int penaltyScore = penaltyTime * PENALTY_SCORE_FOR_FIVE_SECOND;
	return DEFAULT_SCORE - penaltyScore;
    }

    @Override
    public void computeFlyAway() {
        ExceptionRuntimeUtility.checkException(!this.isAlive(), new IllegalStateException());
        EntityUtilities.computeFlyAway(this);
    }
}
