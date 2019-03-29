package model.test;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import javafx.scene.shape.Rectangle;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.EntityStatus;
import model.entities.StandardDuck;
import model.factories.DuckFactory;
import model.factories.DuckFactoryImpl;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.properties.VelocityImpl;
import model.spawner.duck.SpawnSide;

/**
 * 
 * Test for ducks exception.
 *
 */
public class DuckExceptionTest {

    private static final double INITIAL_POSITION_X = 0.0;
    private static final double INITIAL_POSITION_Y = 0.0;
    private static final double VELOCITY_X = 0.0;
    private static final double VELOCITY_Y = 0.0;
    private static final Velocity VELOCITY = new VelocityImpl(VELOCITY_X, VELOCITY_Y);
    private static final DuckDirection DIRECTION = SpawnSide.getSpawnSide();

    private static final String EXPECTED_ILLEGAL = "Should raise a IllegalStateException";
    private static final String NOT_EXPECTED = "Shouldn't raise any Exception";

    private final DuckFactory duckFactory = new DuckFactoryImpl();

    private Duck createStandardDuck() {
        return new StandardDuck(new Rectangle(INITIAL_POSITION_X, INITIAL_POSITION_Y, 
                                    StandardDuck.WIDTH_DUCK, StandardDuck.HEIGHT_DUCK),
                                    VELOCITY,
                                    DIRECTION,
                                    DuckProperty.STANDARD_DUCK);
    }

    /**
     * Test for not allowed change status(Example: Duck dead can't become alive).
     */
    @Test
    public void notAllowedChangeStatus() {
        Duck duck = this.createStandardDuck();
        Duck duckDecorator;

        try {
            duckDecorator = duckFactory.createYellowDuck(duck);
            duckDecorator.kill();
            duckDecorator.setStatus(EntityStatus.ALIVE);
            fail(EXPECTED_ILLEGAL);
        } catch (IllegalStateException e) {
            System.out.println("YELLOW DUCK, NOT ALLOWED KILLED -> ALIVE: OK");
        } catch (Exception e) {
            fail(EXPECTED_ILLEGAL);
        }

        duck = this.createStandardDuck();
        try {
            duckDecorator = duckFactory.createOrangeDuck(duck);
            duckDecorator.computeFlyAway();
            duckDecorator.setStatus(EntityStatus.DEAD);
            fail(EXPECTED_ILLEGAL);
        } catch (IllegalStateException e) {
            System.out.println("ORANGE DUCK, NOT ALLOWED FLOWN_AWAY -> ALIVE: OK");
        } catch (Exception e) {
            fail(EXPECTED_ILLEGAL);
        }

        duck = this.createStandardDuck();
        try {
            duckDecorator = duckFactory.createPinkDuck(duck);
            duckDecorator.computeFlyAway();
            duckDecorator.kill();
            fail(EXPECTED_ILLEGAL);
        } catch (IllegalStateException e) {
            System.out.println("PINK DUCK, NOT ALLOWED FLOWN_AWAY -> DEAD: OK");
        } catch (Exception e) {
            fail(EXPECTED_ILLEGAL);
        }

        duck = this.createStandardDuck();
        try {
            duckDecorator = duckFactory.createYellowDuck(duck);
            duckDecorator.computeFlyAway();
            duckDecorator.getScore();
            fail(EXPECTED_ILLEGAL);
        } catch (IllegalStateException e) {
            System.out.println("YELLOW DUCK, NOT ALLOWED GET SCORE IF NOT DEAD: OK");
        } 
    }

    /**
     * Test for allowed change status of the duck.
     */
    @Test
    public void allowedChangeStatus() {
        Duck duck = this.createStandardDuck();
        Duck duckDecorator;
        try {
            duckDecorator = duckFactory.createYellowDuck(duck);
            duckDecorator.kill();
            System.out.println("YELLOW DUCK, CHANGE STATUS ALIVE -> DEAD: OK");
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }

        duck = this.createStandardDuck();
        try {
            duckDecorator = duckFactory.createOrangeDuck(duck);
            duckDecorator.computeFlyAway();
            System.out.println("ORANGE DUCK, CHANGE STATUS ALIVE -> FLOWN_AWAY: OK");
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }

        duck = this.createStandardDuck();
        try {
            duckDecorator = duckFactory.createPinkDuck(duck);
            duckDecorator.kill();
            System.out.println("PINK DUCK, CHANGE STATUS ALIVE -> DEAD: OK");
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }

        duck = this.createStandardDuck();
        try {
            duck = duckFactory.createPinkDuck(duck);
            duck.kill();
            duck.getScore();
            System.out.println("STANDARD DUCK, GOT SCORE: OK");
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }
    }
}
