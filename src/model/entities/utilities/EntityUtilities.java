package model.entities.utilities;

import model.decorator.YellowDuck;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.EntityStatus;
import model.entities.StandardDuck;

public final class EntityUtilities {
    
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
}
