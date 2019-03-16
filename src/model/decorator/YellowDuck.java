package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;

/**
 * 
 * This class represents a yellow duck.
 *
 */
public final class YellowDuck extends DuckDecorator {

    /**
     * 
     * @param duck
     *          duck decorator
     */
    public YellowDuck(final Duck duck) {
        super(duck);
    }

    @Override
    public int getScoreMultiplier() {
        return DuckProperty.YELLOW_DUCK.getMultiplierScore();
    }
}
