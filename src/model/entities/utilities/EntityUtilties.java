package model.entities.utilities;

import model.decorator.YellowDuck;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.StandardDuck;

public final class EntityUtilties {
    
    /**
     * 
     * @param duck
     * 		duck's type
     * @param time
     * 		time is the time after that duck must fly away.
     * @return true if duck can fly away.
     */
    public static boolean computeFlyAway(final Duck duck) {
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
	return duck.getTimeFromBirth() >= time;
    }
}
