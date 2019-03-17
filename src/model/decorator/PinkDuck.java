package model.decorator;

import model.entities.Duck;

/**
 * 
 * This class represents a pink duck.
 *
 */
public final class PinkDuck extends DuckDecorator {

    private static final int SCORE_MULTIPLIER = 4;

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
        return SCORE_MULTIPLIER;
    }
}
