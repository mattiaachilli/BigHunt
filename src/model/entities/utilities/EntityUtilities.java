package model.entities.utilities;

import model.decorator.YellowDuck;
import java.util.Random;

import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.EntityStatus;
import model.entities.StandardDuck;
import model.spawner.duck.DelayDuckSpawner;

public final class EntityUtilities {
    
    private static final int MILLIS = 1000;
    /**
     * Compute fly away of the duck, if possible.
     * 
     * @param duck
     * 		duck's type
     */
    public static void computeFlyAway(final Duck duck) {
	int time;
	if(duck instanceof StandardDuck) {
	    time = DuckProperty.STANDARD_DUCK.getTimeFlyAway();
	} else if(duck instanceof YellowDuck) {
	    time = DuckProperty.YELLOW_DUCK.getTimeFlyAway();
	} else if(duck instanceof OrangeDuck) {
	    time = DuckProperty.ORANGE_DUCK.getTimeFlyAway();
	} else if(duck instanceof PinkDuck) {
	    time = DuckProperty.PINK_DUCK.getTimeFlyAway();
	} else {
	    throw new IllegalArgumentException();
	}
	if(duck.getTimeFromBirth() >= time) {
	    duck.setStatus(EntityStatus.FLOWN_AWAY);
	}
    }
    
    /**
     * Generate a random number between 1000(ONE MILLISECOND)-maxRange;
     * 
     * @param maxRange 
     *          max number to generate random number.
     * @return random number approximated
     */
    public static int getRandomDelay(final int maxRange) {
        int numberRandom = new Random().nextInt(maxRange) + MILLIS;
        if(numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(0) && 
            numberRandom < DelayDuckSpawner.ALL_DEFAULT_DELAY.get(1)) {
            
            numberRandom = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(0);
        } else if(numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(1) && 
            numberRandom < DelayDuckSpawner.ALL_DEFAULT_DELAY.get(2)) {
            
            numberRandom = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(1);
        } else if(numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(2) && 
            numberRandom < DelayDuckSpawner.ALL_DEFAULT_DELAY.get(3)) {
            
            numberRandom = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(2);
        } else if(numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(3) && 
            numberRandom < DelayDuckSpawner.ALL_DEFAULT_DELAY.get(4)) {
            
            numberRandom = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(3);
        } else if(numberRandom >= DelayDuckSpawner.ALL_DEFAULT_DELAY.get(4)) {
            
            numberRandom = DelayDuckSpawner.ALL_DEFAULT_DELAY.get(4); 
        }
        return numberRandom;
    }
}
