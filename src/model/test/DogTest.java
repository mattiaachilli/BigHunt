package model.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.ModelImpl;
import model.entities.Dog;
import model.entities.DogImpl;
import model.entities.DogStatus;
import model.properties.Velocity;
import model.properties.VelocityImpl;

/**
 * 
 * Test for the dog.
 *
 */
public class DogTest {
    private static final double INIT_POS_X = 0;
    private static final double INIT_POS_Y = ModelImpl.GAME_HEIGHT / 2 + ModelImpl.GAME_HEIGHT / 3;
    private static final double FINAL_POS_X = ModelImpl.GAME_WIDTH / 2;
    private static final double FINAL_POS_Y = ModelImpl.GAME_HEIGHT / 2 + ModelImpl.GAME_HEIGHT / 4 - 100;
    private static final int UPDATE_MOVEMENT = 500;
    private static final int UPDATE_GAME_LOOP = 16;
    private static final Velocity ATTENTION_VELOCITY = new VelocityImpl(0, 0);
    private final Dog dog = new DogImpl();

    /**
     * First test, when game starts and dog walks.
     */
    @Test
    public void testDogStart() {
        assertEquals(INIT_POS_X, dog.getPosition().getX());
        assertEquals(INIT_POS_Y, dog.getPosition().getY());
        assertEquals(DogStatus.RIGHT, dog.getDogStatus());
        assertFalse(dog.isInGrass());
    }

    /**
     * Test for the movement.
     */
    @Test
    public void testDogMovement() {
        dog.update(UPDATE_MOVEMENT);
        assertEquals(DogStatus.SNIFF, dog.getDogStatus());
        dog.update(UPDATE_MOVEMENT);
        assertEquals(DogStatus.RIGHT, dog.getDogStatus());
        dog.update(UPDATE_MOVEMENT);
        assertEquals(DogStatus.SNIFF, dog.getDogStatus());
        dog.update(UPDATE_MOVEMENT);
        assertEquals(DogStatus.RIGHT, dog.getDogStatus());
        assertFalse(dog.isInGrass());
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
        assertEquals(DogStatus.ATTENTION, dog.getDogStatus());
        assertEquals(dog.getVelocity().getX(), ATTENTION_VELOCITY.getX());
        assertEquals(dog.getVelocity().getY(), ATTENTION_VELOCITY.getY());
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
        assertEquals(DogStatus.JUMP, dog.getDogStatus());
    }

    /***
     * Test if dog goes in grass.
     */
    @Test
    public void testDogInGrass() {
        this.testDogJump();
        dog.update(UPDATE_GAME_LOOP);
        assertEquals(DogStatus.IN_GRASS, dog.getDogStatus());
        assertTrue(dog.isInGrass());
        assertTrue(dog.getPosition().getX() >= FINAL_POS_X);
        assertTrue(dog.getPosition().getY() <= FINAL_POS_Y);
    }

    /**
     * Test if dog laugh(exit from the grass).
     */
    @Test
    public void testDogLaugh() {
        this.testDogInGrass();
        dog.setDogStatus(DogStatus.LAUGH);
        assertFalse(dog.isInGrass());
    }

    /**
     * Test if dog is happy(exit from the grass).
     */
    @Test
    public void testDogHappy() {
        this.testDogInGrass();
        dog.setDogStatus(DogStatus.HAPPY);
        assertFalse(dog.isInGrass());
    }
}
