package model.spawner.duck;

import java.util.Arrays;
import java.util.List;

import model.entities.utilities.EntityUtilities;

/**
 * 
 * This enum represents the delay between two ducks in a round in milliseconds.
 * 
 * Each time we get the delay, it will generate a new again.
 *
 */

public enum DelayDuckSpawner {

    
    ROUND_DELAY(2000), //Represents the delay between two rounds.
    
    FIRST_ROUND_DELAY(5000),
    
    SECOND_ROUND_DELAY(5000),
    
    THIRD_ROUND_DELAY(4000),
    
    FOURTH_ROUND_DELAY(4000),
    
    FIFTH_ROUND_DELAY(3000);
    
    public static final List<Integer> ALL_DEFAULT_DELAY = Arrays.asList(1000, 2000, 3000, 4000, 5000);
    private int secondRandomDelay;
    
    private DelayDuckSpawner(final int secondRandomDelay) {
        this.secondRandomDelay = secondRandomDelay;
    }
    
    public int getSecondDelay() {
        if(this == ROUND_DELAY) {
            return this.secondRandomDelay;
        }
        this.setNewRandomDelay();
        return this.secondRandomDelay;
    }
    
    public void setNewRandomDelay() {
        int newDelay = 0;

        switch(this) {
            case FIRST_ROUND_DELAY:
                newDelay = EntityUtilities.getRandomDelay(ALL_DEFAULT_DELAY.get(4));
                break;
            case SECOND_ROUND_DELAY:
                newDelay = EntityUtilities.getRandomDelay(ALL_DEFAULT_DELAY.get(4));
                break;
            case THIRD_ROUND_DELAY:
                newDelay = EntityUtilities.getRandomDelay(ALL_DEFAULT_DELAY.get(3));
                break;
            case FOURTH_ROUND_DELAY:
                newDelay = EntityUtilities.getRandomDelay(ALL_DEFAULT_DELAY.get(3));
                break;
            case FIFTH_ROUND_DELAY:
                newDelay = EntityUtilities.getRandomDelay(ALL_DEFAULT_DELAY.get(2));
                break;
            default:
                break;
        }
        this.secondRandomDelay = newDelay;
    }
}


