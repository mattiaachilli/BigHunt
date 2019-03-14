package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;

public final class YellowDuck extends DuckDecorator {

    public YellowDuck(final Duck duck) {
	super(duck);
    }

    @Override
    public int getScoreMultiplier() {
	return DuckProperty.YELLOW_DUCK.getMultiplierScore();
    }
}
