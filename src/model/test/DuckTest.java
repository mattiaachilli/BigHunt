package model.test;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import javafx.scene.shape.Rectangle;
import model.entities.Duck;
import model.entities.EntityStatus;
import model.entities.StandardDuck;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.properties.VelocityImpl;
import model.spawner.duck.SpawnSideUtil;

/**
 * 
 * Test for a StandardDuck.
 *
 */
public class DuckTest {
    private static final String ERROR_MESSAGE = "Error";

    private static final double INITIAL_POSITION_X = 200.0;
    private static final double INITIAL_POSITION_Y = 200.0;
    private static final double STANDARD_VELOCITY_X = StandardDuck.VELOCITY;
    private static final double STANDARD_VELOCITY_Y = StandardDuck.VELOCITY;
    private static final double MODIFIED_VELOCITY_X = 10.0; //Movement without collisions
    private static final double MODIFIED_VELOCITY_Y = 10.0;
    private static final Velocity STANDARD_VELOCITY = new VelocityImpl(STANDARD_VELOCITY_X, STANDARD_VELOCITY_Y);
    private static final Velocity MODIFIED_VELOCITY = new VelocityImpl(MODIFIED_VELOCITY_X, MODIFIED_VELOCITY_Y);
    private static final DuckDirection DIRECTION = SpawnSideUtil.getSpawnSide();
    private static final int SECONDS_ELAPSED = 1000; //For update

    private Duck duck;

    private void resetPosition(final Velocity velocity) {
        duck = new StandardDuck(new Rectangle(INITIAL_POSITION_X, INITIAL_POSITION_Y,
                                    StandardDuck.WIDTH_DUCK, StandardDuck.HEIGHT_DUCK),
                                    velocity,
                                    DIRECTION);
    }

    private void flyAwayDuck() {
        this.duck.computeFlyAway();
    }

    /**
     * Test the position of the duck.
     */
    @Test
    public void testInitPosition() {
        resetPosition(new VelocityImpl(0, 0));
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X, ERROR_MESSAGE);
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y, ERROR_MESSAGE);
    }

    /**
     * Test duck's position without collision and without random movement.
     */
    @Test
    public void testMovementWithoutCollision() {
        this.resetPosition(MODIFIED_VELOCITY);
        this.duck.setMovementChange(false);
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + MODIFIED_VELOCITY_X, ERROR_MESSAGE);
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + MODIFIED_VELOCITY_Y, ERROR_MESSAGE);
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + (MODIFIED_VELOCITY_X * 2), ERROR_MESSAGE);
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + (MODIFIED_VELOCITY_Y * 2), ERROR_MESSAGE);
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + (MODIFIED_VELOCITY_X * 3), ERROR_MESSAGE);
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + (MODIFIED_VELOCITY_Y * 3), ERROR_MESSAGE);
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + (MODIFIED_VELOCITY_X * 4), ERROR_MESSAGE);
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + (MODIFIED_VELOCITY_Y * 4), ERROR_MESSAGE);
    }

    /**
     * Test all data about duck.
     */
    @Test
    public void testDuckStatus() {
        this.resetPosition(STANDARD_VELOCITY);
        this.duck.setMovementChange(true);
        //Test initMovement
        assertEquals(this.duck.getActualDirection(), DIRECTION, ERROR_MESSAGE);
        assertEquals(this.duck.getStatus(), EntityStatus.ALIVE, ERROR_MESSAGE);
        assertTrue(ERROR_MESSAGE, this.duck.isAlive());
    }

    /**
     * Test on fly away.
     */
    @Test
    public void testFlyAway() {
        this.resetPosition(STANDARD_VELOCITY);
        this.duck.setMovementChange(true);
        assertFalse(ERROR_MESSAGE, this.duck.canFlyAway());
        this.flyAwayDuck();
        assertEquals(this.duck.getStatus(), EntityStatus.FLOWN_AWAY, ERROR_MESSAGE);
        assertEquals(this.duck.getActualDirection(), DuckDirection.FLOWN_AWAY, ERROR_MESSAGE);
    }

    /**
     * Test kill duck.
     */
    @Test
    public void testKill() {
        this.resetPosition(STANDARD_VELOCITY);
        this.duck.setMovementChange(true);
        assertTrue(ERROR_MESSAGE, this.duck.isAlive());
        this.duck.kill();
        assertEquals(this.duck.getStatus(), EntityStatus.DEAD, ERROR_MESSAGE);
        assertEquals(this.duck.getActualDirection(), DuckDirection.KILLED, ERROR_MESSAGE);
    }
}
