package model.decorator;

import model.entities.Duck;

/**
 * 
 * This class represents a orange duck.
 *
 */
public final class OrangeDuck extends DuckDecorator {

    private static final int SCORE_MULTIPLIER = 3;

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
        return SCORE_MULTIPLIER;
    }

}
