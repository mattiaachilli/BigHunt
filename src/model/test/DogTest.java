package model.test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.ModelImpl;
import model.entities.Dog;
import model.entities.DogImpl;
import model.entities.DogStatus;
import model.entities.EntityStatus;
import model.properties.Velocity;
import model.properties.VelocityImpl;

/**
 * 
 * Test for the dog.
 *
 */
public class DogTest {
    private static final String ERROR_MESSAGE = "Error";

    private static final double INIT_POS_X = 0;
    private static final double INIT_POS_Y = ModelImpl.GAME_HEIGHT * 0.85;
    private static final double FINAL_POS_X = ModelImpl.GAME_WIDTH / 2;
    private static final double FINAL_POS_Y = ModelImpl.GAME_HEIGHT * 0.70;
    private static final int UPDATE_MOVEMENT = 500;
    private static final int UPDATE_GAME_LOOP = 16;
    private static final Velocity ATTENTION_VELOCITY = new VelocityImpl(0, 0);
    private final Dog dog = new DogImpl();
    private static final String EXPECTED_ILLEGAL = "Should raise a UnsupportedOperationException";

    /**
     * First test, when game starts and dog walks.
     */
    @Test
    public void testDogStart() {
        assertEquals(INIT_POS_X, dog.getPosition().getX(), ERROR_MESSAGE);
        assertEquals(INIT_POS_Y, dog.getPosition().getY(), ERROR_MESSAGE);
        assertEquals(DogStatus.RIGHT, dog.getDogStatus(), ERROR_MESSAGE);
        assertFalse(ERROR_MESSAGE, dog.isInGrass());
    }

    /**
     * Test for the movement.
     */
    @Test
    public void testDogMovement() {
        dog.update(UPDATE_MOVEMENT);
        assertEquals(DogStatus.SNIFF, dog.getDogStatus(), ERROR_MESSAGE);
        dog.update(UPDATE_MOVEMENT);
        assertEquals(DogStatus.RIGHT, dog.getDogStatus(), ERROR_MESSAGE);
        dog.update(UPDATE_MOVEMENT);
        assertEquals(DogStatus.SNIFF, dog.getDogStatus(), ERROR_MESSAGE);
        dog.update(UPDATE_MOVEMENT);
        assertEquals(DogStatus.RIGHT, dog.getDogStatus(), ERROR_MESSAGE);
        assertFalse(ERROR_MESSAGE, dog.isInGrass());
    }

    /**
     * Test for attention, when he arrived at final x position.
     */
    @Test
    public void testDogAttention() {
        while (dog.getPosition().getX() <= FINAL_POS_X) {
            dog.update(UPDATE_GAME_LOOP);
        }
        dog.update(UPDATE_GAME_LOOP);
        assertEquals(DogStatus.ATTENTION, dog.getDogStatus(), ERROR_MESSAGE);
        assertEquals(dog.getVelocity().getX(), ATTENTION_VELOCITY.getX(), ERROR_MESSAGE);
        assertEquals(dog.getVelocity().getY(), ATTENTION_VELOCITY.getY(), ERROR_MESSAGE);
    }

    /**
     * Test if dog jump after attention.
     */
    @Test
    public void testDogJump() {
        this.testDogAttention();
        while (dog.getPosition().getY() >= FINAL_POS_Y) {
            dog.update(UPDATE_GAME_LOOP);
        }
        assertEquals(DogStatus.JUMP, dog.getDogStatus(), ERROR_MESSAGE);
    }

    /***
     * Test if dog goes in grass.
     */
    @Test
    public void testDogInGrass() {
        this.testDogJump();
        dog.update(UPDATE_GAME_LOOP);
        assertEquals(DogStatus.IN_GRASS, dog.getDogStatus(), ERROR_MESSAGE);
        assertTrue(ERROR_MESSAGE, dog.isInGrass());
        assertTrue(ERROR_MESSAGE, dog.getPosition().getX() >= FINAL_POS_X);
        assertTrue(ERROR_MESSAGE, dog.getPosition().getY() <= FINAL_POS_Y);
    }

    /**
     * Test if dog laugh(exit from the grass).
     */
    @Test
    public void testDogLaugh() {
        this.testDogInGrass();
        dog.setDogStatus(DogStatus.LAUGH);
        assertFalse(ERROR_MESSAGE, dog.isInGrass());
    }

    /**
     * Test if dog is happy(exit from the grass).
     */
    @Test
    public void testDogHappy() {
        this.testDogInGrass();
        dog.setDogStatus(DogStatus.HAPPY);
        assertFalse(ERROR_MESSAGE, dog.isInGrass());
    }

    /**
     * Test dog for not allowed kill.
     */
    @Test
    public void testDogKillException() {
        try {
            this.dog.kill();
            fail(EXPECTED_ILLEGAL);
        } catch (UnsupportedOperationException e) {
            System.out.println("DOG KILL NOT ALLOWED: OK");
        } catch (Exception e) {
            fail(EXPECTED_ILLEGAL);
        }
    }

    /**
     * Test dog for not allowed change status.
     */
    @Test
    public void testDogChangeStatusException() {
        try {
            this.dog.setStatus(EntityStatus.DEAD);
            fail(EXPECTED_ILLEGAL);
        } catch (UnsupportedOperationException e) {
            System.out.println("DOG CHANGE STATUS NOT ALLOWED: OK");
        } catch (Exception e) {
            fail(EXPECTED_ILLEGAL);
        }
    }
}
