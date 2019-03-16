package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;

/**
 * 
 * This class represents a orange duck.
 *
 */
public final class OrangeDuck extends DuckDecorator {

    /**
     * 
     * @param duck 
     *          duck decorator
     */
    public OrangeDuck(final Duck duck) {
        super(duck);
    }

    @Override
    public int getScoreMultiplier() {
        return DuckProperty.ORANGE_DUCK.getMultiplierScore();
    }
}
