package model.entities;

import java.util.Optional;
import java.util.Random;

import javafx.scene.shape.Shape;
import model.ModelImpl;
import model.entities.powerup.PowerUp;
import model.entities.utilities.EntityUtilities;
import model.factories.PowerUpFactoryImpl;
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

    private static final int MILLIS_UPDATE_DIRECTION = 1500; //1.5 SECONDS
    private static final int MILLIS_UPDATE_PRECIPITATE = 300; //300ms
    private static final int DEFAULT_SCORE = 50;
    private static final int POSSIBLE_DIRECTION = 6;
    /**
     * WIDTH OF THE DUCK.
     */
    public static final int WIDTH_DUCK = ModelImpl.GAME_HEIGHT / 10;
    /**
     * HEIGHT OF THE DUCK.
     */
    public static final int HEIGHT_DUCK = ModelImpl.GAME_HEIGHT / 10;
    /**
     * X COORDINATE COLLISION.
     */
    public static final double COLLISION_X = ModelImpl.GAME_WIDTH / 30;
    /**
     * Y COORDINATE COLLISION.
     */
    public static final double COLLISION_Y = ModelImpl.GAME_WIDTH / 30;

    private DuckDirection actualDirection;
    private DuckProperty duckType;
    private int lastDirectionUpdate;
    private Optional<PowerUp> powerUp;
    private boolean movement;
    private boolean decelerate;

    /**
     * Standard Duck constructor.
     * 
     * @param shape
     *          of the duck
     * @param velocity
     *          of the duck
     * @param duckDirection
     *          initial direction
     * @param duckType
     *          duck's type.
     */
    public StandardDuck(final Shape shape, final Velocity velocity, final DuckDirection duckDirection, final DuckProperty duckType) {
        super(shape, velocity);
        this.powerUp = PowerUpFactoryImpl.getInstance().createRandomPowerUp(this.getPosition());
        this.actualDirection = duckDirection;
        this.duckType = duckType;
        this.lastDirectionUpdate = 0;
        this.movement = true;
        this.decelerate = false;
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
        super.kill();
        EntityUtilities.setNewPosition(this, false, DuckDirection.KILLED);
        if (this.hasPowerUp()) {
            this.powerUp.get().setVisible();
        }
        this.lastDirectionUpdate = 0;
    }

    @Override
    public final void update(final int timeElapsed) {
        if (this.getStatus() == EntityStatus.ALIVE && this.movement) { 
            this.lastDirectionUpdate += timeElapsed;
            if (this.canChangeDirection()) {
                this.lastDirectionUpdate -= MILLIS_UPDATE_DIRECTION;
                this.changeDirection();
            }
            EntityUtilities.checkCollision(this, this.decelerate, this.actualDirection);
            if (this.hasPowerUp()) {
                this.powerUp.get().setPosition(this.getPosition());
            }
            super.addTimeElapsed(timeElapsed);
        } else if (this.getStatus() == EntityStatus.DEAD) {
            this.lastDirectionUpdate += timeElapsed;
            if (this.lastDirectionUpdate >= MILLIS_UPDATE_PRECIPITATE) {
                EntityUtilities.setNewPosition(this, this.decelerate, DuckDirection.PRECIPITATE);
            }
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
                EntityUtilities.setNewPosition(this, this.decelerate, direction.getLeft());
            }
        }
    }

    @Override
    public final int getScore() {
        ExceptionRuntimeUtility.checkException(this.isAlive() || this.getStatus() == EntityStatus.FLOWN_AWAY, new IllegalStateException());
        return DEFAULT_SCORE;
    }

    @Override
    public final boolean canFlyAway() {
        return EntityUtilities.checkFlyAway(this);
    }

    @Override
    public final void computeFlyAway() {
        ExceptionRuntimeUtility.checkException(!this.isAlive(), new IllegalStateException());
        this.setStatus(EntityStatus.FLOWN_AWAY);
        EntityUtilities.setNewPosition(this, false, DuckDirection.FLOWN_AWAY);
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

    @Override
    public final void setDecelerate() {
        if (!this.decelerate) {
            this.decelerate = true;
        }
    }

    @Override
    public final DuckProperty getProperty() {
        return this.duckType;
    }
}
