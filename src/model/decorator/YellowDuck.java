package model.decorator;

import model.ModelImpl;
import model.entities.Duck;
import model.entities.EntityStatus;

/**
 * 
 * This class represents a yellow duck.
 *
 */
public final class YellowDuck extends DuckDecorator {

    private static final int SCORE_MULTIPLIER = 2;
    /**
     * Velocity of the duck.
     */
    public static final double VELOCITY = ModelImpl.GAME_WIDTH / 3.5 + 200;
    private static final int FLY_AWAY_TIME = 7000;


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

    @Override
    public boolean canFlyAway() {
        return this.getTimeElapsed() >= FLY_AWAY_TIME && this.getStatus() == EntityStatus.ALIVE;
    }
}
