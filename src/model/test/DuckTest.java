package model.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import javafx.scene.shape.Rectangle;
import model.ModelImpl;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.EntityStatus;
import model.entities.StandardDuck;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.properties.VelocityImpl;
import model.spawner.duck.SpawnSide;

/**
 * 
 * Test for a StandardDuck
 *
 */
public class DuckTest {
    private static final double INITIAL_POSITION_X = 200.0;
    private static final double INITIAL_POSITION_Y = 200.0;
    private static final double STANDARD_VELOCITY_X = DuckProperty.STANDARD_DUCK.getVelocity();
    private static final double STANDARD_VELOCITY_Y = DuckProperty.STANDARD_DUCK.getVelocity();
    private static final double MODIFIED_VELOCITY_X = 10.0; //Movement without collisions
    private static final double MODIFIED_VELOCITY_Y = 10.0;
    private static final double LIMIT_Y = ModelImpl.GAME_HEIGHT / 2 + ModelImpl.GAME_HEIGHT / 4;
    private static final Velocity STANDARD_VELOCITY = new VelocityImpl(STANDARD_VELOCITY_X, STANDARD_VELOCITY_Y);
    private static final Velocity MODIFIED_VELOCITY = new VelocityImpl(MODIFIED_VELOCITY_X, MODIFIED_VELOCITY_Y);
    private static final DuckDirection DIRECTION = SpawnSide.getSpawnSide();
    private static final int SECONDS_ELAPSED = 1000; //For update
    
    private Duck duck;
    
    
    private void resetPosition(final Velocity velocity) {
        duck = new StandardDuck(new Rectangle(INITIAL_POSITION_X, INITIAL_POSITION_Y
                                , StandardDuck.WIDTH_DUCK, StandardDuck.HEIGHT_DUCK) 
                                , velocity
                                , DIRECTION);
    }
    
    private void flyAwayDuck() {
        this.duck.computeFlyAway();
    }
    
    /**
     * Test the position of the duck
     */
    @Test
    public void testInitPosition() {
        resetPosition(new VelocityImpl(0, 0));
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X);
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y);
    }
    
    /**
     * Test duck's position without collision and without random movement
     */
    @Test
    public void testMovementWithoutCollision() {
        this.resetPosition(MODIFIED_VELOCITY);
        this.duck.setMovementChange(false);
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + MODIFIED_VELOCITY_X);
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + MODIFIED_VELOCITY_Y);
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + (MODIFIED_VELOCITY_X * 2));
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + (MODIFIED_VELOCITY_Y * 2));
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + (MODIFIED_VELOCITY_X * 3));
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + (MODIFIED_VELOCITY_Y * 3));
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + (MODIFIED_VELOCITY_X * 4));
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + (MODIFIED_VELOCITY_Y * 4));
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + (MODIFIED_VELOCITY_X * 5));
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + (MODIFIED_VELOCITY_Y * 5));
    }
    
    /**
     * Test duck's standard movement, don't test movement with collisions or random because would be too difficult to do(I tested it by View). 
     */
    @Test
    public void testStandardMovement() {
        this.resetPosition(STANDARD_VELOCITY);
        this.duck.setMovementChange(true);
        this.duck.update(SECONDS_ELAPSED);
        assertEquals(this.duck.getPosition().getX(), INITIAL_POSITION_X + STANDARD_VELOCITY_X);
        assertTrue(this.duck.getPosition().getY() > LIMIT_Y);
        assertEquals(this.duck.getPosition().getY(), INITIAL_POSITION_Y + STANDARD_VELOCITY_Y); 
    }
    
    /**
     * Test all data about duck.
     */
    @Test
    public void testDuckStatus(){
        this.resetPosition(STANDARD_VELOCITY);
        this.duck.setMovementChange(true);
        //Test initMovement
        assertEquals(this.duck.getActualDirection(), DIRECTION);
        assertEquals(this.duck.getStatus(), EntityStatus.ALIVE);
        assertTrue(this.duck.isAlive());
    }
    
    /**
     * Test on fly away.
     */
    @Test
    public void testFlyAway() {
        this.resetPosition(STANDARD_VELOCITY);
        this.duck.setMovementChange(true);
        assertFalse(this.duck.canFlyAway());
        this.flyAwayDuck();
        assertEquals(this.duck.getStatus(), EntityStatus.FLOWN_AWAY);
        assertEquals(this.duck.getActualDirection(), DuckDirection.FLOWN_AWAY);
    }
    
    @Test
    public void testKill() {
        this.resetPosition(STANDARD_VELOCITY);
        this.duck.setMovementChange(true);
        assertTrue(this.duck.isAlive());
        this.duck.kill();
        assertEquals(this.duck.getStatus(), EntityStatus.DEAD);
        assertEquals(this.duck.getActualDirection(), DuckDirection.KILLED);
    }
}
