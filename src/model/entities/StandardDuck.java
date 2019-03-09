package model.entities;

import java.util.Optional;
import java.util.Random;

import javafx.scene.shape.Shape;
import model.entities.powerup.PowerUp;
import model.entities.utilities.EntityUtilities;
import model.properties.DuckDirection;
import model.properties.Position;
import model.properties.PositionImpl;
import model.properties.Velocity;
import model.utilities.ExceptionRuntimeUtility;
import org.apache.commons.lang3.tuple.Pair;

/**
 * 
 * This class represents a standard duck.
 *
 */

public class StandardDuck extends AbstractCharacter implements Duck {
    
    private static final int MILLIS_UPDATE_DIRECTION = 2000; //2 SECONDS
    private static final int DEFAULT_SCORE = 50;
    private static final int TIME_FOR_PENALTY_SCORE = 5000; //5 SECONDS
    private static final int PENALTY_SCORE_FOR_FIVE_SECOND = 5;
    private static final int POSSIBLE_DIRECTION = 8;
    
    private final long initTime; //Duck's creation time
    private long dieTime; //Duck's died time
    private DuckDirection actualDirection;
    private int lastDirectionUpdate;
    private Optional<PowerUp> powerUp;

    public StandardDuck(final Shape shape, final Velocity velocity, final DuckDirection duckDirection) {
	super(shape, velocity);
	this.initTime = System.currentTimeMillis();
	this.powerUp = this.getRandomPowerUp();
	this.actualDirection = duckDirection;
	this.lastDirectionUpdate = 0;
    }
    
    private Optional<PowerUp> getRandomPowerUp() {
        /**
         * Algoritmo con le rarità
         */
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
	this.actualDirection = DuckDirection.KILLED;
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
    public void update(final int timeElapsed) {
        this.lastDirectionUpdate += timeElapsed;
        if(this.canUpdateVelocity()) {
            this.lastDirectionUpdate -= MILLIS_UPDATE_DIRECTION;
            this.changeDirection();
        }
        super.update(timeElapsed);
    }
    
    private boolean canUpdateVelocity() {
        return this.lastDirectionUpdate >= MILLIS_UPDATE_DIRECTION;
    }
    
    private void changeDirection() {
        int randomDirection = new Random().nextInt(POSSIBLE_DIRECTION) + 1;
       
        for(Pair<DuckDirection, Integer> direction: DuckDirection.getRandomDirection()) {
            if(direction.getRight() == randomDirection) {
                //
            }
        }
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
        if(this.getStatus() == EntityStatus.FLOWN_AWAY) {
            this.setDirection(DuckDirection.FLOWN_AWAY);
        }
    }
    
    private void setDirection(final DuckDirection newDuckDirection) {
        this.actualDirection = newDuckDirection;
    }

    @Override
    public DuckDirection getActualDirection() {
        return this.actualDirection;
    }
}
