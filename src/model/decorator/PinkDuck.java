package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;

/**
 * 
 * This class represents a pink duck.
 *
 */
public final class PinkDuck extends DuckDecorator {

    /**
     * 
     * @param duck
     *          duck decorator
     */
    public PinkDuck(final Duck duck) {
        super(duck);
    }

    @Override
    public int getScoreMultiplier() {
        return DuckProperty.PINK_DUCK.getMultiplierScore();
    }
}
