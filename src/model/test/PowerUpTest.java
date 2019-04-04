package model.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.scene.shape.Rectangle;
import model.ModelImpl;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.StandardDuck;
import model.entities.powerup.PowerUp;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.properties.VelocityImpl;

/**
 *
 * Test for PowerUp.
 *
 */
public class PowerUpTest {
    private static final Velocity INIT_POWER_UP_VELOCITY = new VelocityImpl(0, 0);
    private static final Velocity FINAL_POWER_UP_VELOCITY = new VelocityImpl(0.0, ModelImpl.GAME_HEIGHT / 5);
    private static final int POWER_UP_VERY_COMMON = 5;
    private static final int POWER_UP_COMMON = 11;
    private static final int POWER_UP_RARE = 16;
    private static final int POWER_UP_VERY_RARE = 21;
    private static final int UPDATE_ELAPSED = 2000;


    private Duck createDuck() {
        return new StandardDuck(new Rectangle(0, 0, StandardDuck.WIDTH_DUCK, StandardDuck.HEIGHT_DUCK),
                                    new VelocityImpl(0, 0),
                                    DuckDirection.RIGHT,
                                    DuckProperty.STANDARD_DUCK);
    }

    /**
     * Check if ducks has powerUp.
     */
    @Test
    public void checkDuckGetPowerUp() {
        Duck duck;
        for (int i = 1; i < POWER_UP_VERY_COMMON; i++) {
            duck = this.createDuck();
            assertFalse(duck.hasPowerUp());
        }
        duck = this.createDuck();
        assertTrue(duck.hasPowerUp());
        for (int i = POWER_UP_VERY_COMMON + 1; i < POWER_UP_COMMON; i++) {
            duck = this.createDuck();
            assertFalse(duck.hasPowerUp());
        }
        duck = this.createDuck();
        assertTrue(duck.hasPowerUp());
        assertTrue(duck.getPowerUp().get().getVelocity().compareTo(INIT_POWER_UP_VELOCITY));
        for (int i = POWER_UP_COMMON + 1; i < POWER_UP_RARE; i++) {
            duck = this.createDuck();
            assertFalse(duck.hasPowerUp());
        }
        duck = this.createDuck();
        assertTrue(duck.hasPowerUp());
        assertTrue(duck.getPowerUp().get().getVelocity().compareTo(INIT_POWER_UP_VELOCITY));
        for (int i = POWER_UP_RARE + 1; i < POWER_UP_VERY_RARE; i++) {
            duck = this.createDuck();
            assertFalse(duck.hasPowerUp());
        }
        duck = this.createDuck();
        assertTrue(duck.hasPowerUp());
        assertTrue(duck.getPowerUp().get().getVelocity().compareTo(INIT_POWER_UP_VELOCITY));
    }

    /**
     * Info about powerUp.
     */
    @Test
    public void powerUpInfo() {
        final PowerUp powerUp;
        Duck duck;
        for (int i = 1; i < POWER_UP_VERY_COMMON; i++) {
            duck = this.createDuck();
            assertFalse(duck.hasPowerUp());
        }
        duck = this.createDuck();
        assertTrue(duck.hasPowerUp());
        powerUp = duck.getPowerUp().get();
        assertTrue(powerUp.getVelocity().compareTo(INIT_POWER_UP_VELOCITY));
        duck.update(UPDATE_ELAPSED);
        duck.kill();
        powerUp.update(UPDATE_ELAPSED);
        assertTrue(powerUp.getVelocity().compareTo(FINAL_POWER_UP_VELOCITY));
        assertTrue(powerUp.isVisible());
        powerUp.hit();
        assertTrue(powerUp.isHit());
    }
}
