package model.decorator;

import model.entities.Duck;

/**
 * 
 * This class represents a yellow duck.
 *
 */
public final class YellowDuck extends DuckDecorator {

    private static final int SCORE_MULTIPLIER = 2;

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
        return SCORE_MULTIPLIER;
    }
}
