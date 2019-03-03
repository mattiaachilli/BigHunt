package model.entities;

import java.util.Optional;

import javafx.scene.shape.Shape;
import model.conversions.TimeConversion;
import model.entities.powerup.PowerUp;
import model.entities.utilities.EntityUtilties;
import model.properties.Velocity;

/**
 * 
 * This class represents a standard duck.
 *
 */

public class StandardDuck extends AbstractCharacter implements Duck {
    
    private static final int DEFAULT_SCORE = 50;
    private static final int TIME_FOR_PENALTY_SCORE = 5;
    private static final int PENALTY_SCORE_FOR_FIVE_SECOND = 5;
    
    private final long initTime; //Duck's creation time
    private long dieTime; //Duck's died time
    private boolean flyAway;
    private Optional<PowerUp> powerUp;

    public StandardDuck(final Shape shape, final Velocity velocity) {
	super(shape, velocity);
	this.initTime = TimeConversion.getSecondsByMillis(System.currentTimeMillis());
	this.flyAway = false;
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
    public boolean canFlyAway() {
	if(!this.isAlive()) {
	    throw new IllegalStateException();
	}
	this.flyAway = EntityUtilties.computeFlyAway(this)? true: false;
	return this.flyAway;
    }
    
    @Override
    public void kill() {
	super.kill();
	this.dieTime = TimeConversion.getSecondsByMillis(System.currentTimeMillis());
    }
    
    @Override
    public long getTimeFromBirth() {
	long time;
	if(this.isAlive()) {
	    time = TimeConversion.getSecondsByMillis(System.currentTimeMillis()) -
		    this.initTime;
	} else { //Died
	    time = this.dieTime - this.initTime;
	}
	return time;
    }

    @Override
    public int getScore() {
	final int penaltyTime = (int) this.getTimeFromBirth() / TIME_FOR_PENALTY_SCORE;
	final int penaltyScore = penaltyTime * PENALTY_SCORE_FOR_FIVE_SECOND;
	return DEFAULT_SCORE - penaltyScore;
    }
}
