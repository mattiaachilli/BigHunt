package model.entities;

import java.util.Optional;
import java.util.Random;

import javafx.scene.shape.Shape;
import model.entities.powerup.PowerUp;
import model.entities.utilities.EntityUtilities;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.utilities.ExceptionRuntimeUtility;
import org.apache.commons.lang3.tuple.Pair;

/**
 * 
 * This class represents a standard duck.
 *
 */

public class StandardDuck extends AbstractCharacter implements Duck {

    private static final int MILLIS_UPDATE_DIRECTION = 1000; //1.5 SECONDS
    private static final int DEFAULT_SCORE = 50;
    private static final int TIME_FOR_PENALTY_SCORE = 5000; //5 SECONDS
    private static final int PENALTY_SCORE_FOR_FIVE_SECOND = 5;

    private static final int POSSIBLE_DIRECTION = 6;
    /**
     * WIDTH OF THE DUCK.
     */
    public static final int WIDTH_DUCK = 80;
    /**
     * HEIGHT OF THE DUCK.
     */
    public static final int HEIGHT_DUCK = 80;
    /**
     * X COORDINATE COLLISION.
     */
    public static final double COLLISION_X = 150.0;
    /**
     * Y COORDINATE COLLISION.
     */
    public static final double COLLISION_Y = 150.0;

    private final long initTime; //Duck's creation time
    private long dieTime; //Duck's died time
    private DuckDirection actualDirection;
    private int lastDirectionUpdate;
    private Optional<PowerUp> powerUp;
    private boolean movement;

    /**
     * Standard Duck constructor.
     * 
     * @param shape
     *          of the duck
     * @param velocity
     *          of the duck
     * @param duckDirection
     *          initial direction
     */
    public StandardDuck(final Shape shape, final Velocity velocity, final DuckDirection duckDirection) {
        super(shape, velocity);
        this.initTime = System.currentTimeMillis();
        this.powerUp = this.getRandomPowerUp();
        this.actualDirection = duckDirection;
        this.lastDirectionUpdate = 0;
        this.movement = true;
    }

    private Optional<PowerUp> getRandomPowerUp() {
        /**
         * Algoritmo con le rarità
         */
        return Optional.empty();
    }

    @Override
    public final Optional<PowerUp> getPowerUp() {
        return this.powerUp;
    }

    @Override
    public final boolean hasPowerUp() {
        return this.powerUp.isPresent();
    }

    @Override
    public final void kill() {
        ExceptionRuntimeUtility.checkException(!this.isAlive(), new IllegalStateException());
        super.kill();
        this.dieTime = System.currentTimeMillis();
        EntityUtilities.setNewPosition(this, DuckDirection.KILLED);
    }

    @Override
    public final long getTimeFromBirth() {
        long time;
        if (this.isAlive()) {
            time = System.currentTimeMillis() - this.initTime;
        } else { //Died
            time = this.dieTime - this.initTime;
        }
        return time;
    }

    @Override
    public final void update(final int timeElapsed) {
        if (this.getStatus() == EntityStatus.ALIVE && this.movement) { //Only if is alive
            this.lastDirectionUpdate += timeElapsed;
            if (this.canChangeDirection()) {
                this.lastDirectionUpdate -= MILLIS_UPDATE_DIRECTION;
                this.changeDirection();
            }
            EntityUtilities.checkCollision(this, this.actualDirection);
        }
        super.update(timeElapsed);
    }

    private boolean canChangeDirection() {
        return this.lastDirectionUpdate >= MILLIS_UPDATE_DIRECTION;
    }

    private void changeDirection() {
        int randomDirection = new Random().nextInt(POSSIBLE_DIRECTION) + 1;

        for (Pair<DuckDirection, Integer> direction: DuckDirection.getRandomDirection()) {
            if (direction.getRight() == randomDirection) {
                EntityUtilities.setNewPosition(this, direction.getLeft());
            }
        }
    }

    @Override
    public final int getScore() {
        ExceptionRuntimeUtility.checkException(this.isAlive() || this.getStatus() == EntityStatus.FLOWN_AWAY, new IllegalStateException());
        final int penaltyTime = (int) this.getTimeFromBirth() / TIME_FOR_PENALTY_SCORE;
        final int penaltyScore = penaltyTime * PENALTY_SCORE_FOR_FIVE_SECOND;
        return DEFAULT_SCORE - penaltyScore;
    }

    @Override
    public final boolean canFlyAway() {
        return EntityUtilities.checkFlyAway(this);
    }

    @Override
    public final void computeFlyAway() {
        ExceptionRuntimeUtility.checkException(!this.isAlive(), new IllegalStateException());
        this.setStatus(EntityStatus.FLOWN_AWAY);
        EntityUtilities.setNewPosition(this, DuckDirection.FLOWN_AWAY);
    }

    @Override
    public final void setDirection(final DuckDirection newDuckDirection) {
        this.actualDirection = newDuckDirection;
    }

    @Override
    public final DuckDirection getActualDirection() {
        return this.actualDirection;
    }

    @Override
    public final void setMovementChange(final boolean change) {
        this.movement = change;
    }
}
