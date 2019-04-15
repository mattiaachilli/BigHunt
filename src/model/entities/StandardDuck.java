package model.entities;

import java.util.Optional;

import audio.Sound;
import javafx.scene.shape.Shape;
import model.ModelImpl;
import model.entities.powerup.PowerUp;
import model.entities.utilities.EntityUtilities;
import model.factories.PowerUpFactory;
import model.factories.PowerUpFactoryImpl;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.utilities.ExceptionRuntimeUtility;
import settings.SettingsImpl;

/**
 * 
 * This class represents a standard duck.
 *
 */

public class StandardDuck extends AbstractCharacter implements Duck {

    private static final int MILLIS_UPDATE_DIRECTION = 1000; //1 SECOND
    private static final int MILLIS_UPDATE_PRECIPITATE = 300; //300ms
    private static final int DEFAULT_SCORE = 50;
    /**
     * Velocity of the duck.
     */
    public static final double VELOCITY = ModelImpl.GAME_WIDTH / 3.5;
    private static final int FLY_AWAY_TIME = 8000;
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
     */
    public StandardDuck(final Shape shape, final Velocity velocity, final DuckDirection duckDirection) {
        super(shape, velocity);
        final PowerUpFactory powUpFactory = new PowerUpFactoryImpl();
        this.powerUp = powUpFactory.createRandomPowerUp(this.getPosition());
        this.actualDirection = duckDirection;
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
        if (SettingsImpl.getSettings().isBackgroundAudioOn()) {
            Sound.DUCK_DEAD.play();
        }
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
                EntityUtilities.changeDirection(this);
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

    @Override
    public final int getScore() {
        ExceptionRuntimeUtility.checkException(this.isAlive() || this.getStatus() == EntityStatus.FLOWN_AWAY, new IllegalStateException());
        return DEFAULT_SCORE;
    }

    @Override
    public final boolean canFlyAway() {
        return this.getTimeElapsed() >= FLY_AWAY_TIME && this.getStatus() == EntityStatus.ALIVE;
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
    public final boolean isDecelerated() {
        return this.decelerate;
    }
}
