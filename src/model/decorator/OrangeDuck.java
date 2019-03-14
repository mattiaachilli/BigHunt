package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;

public final class OrangeDuck extends DuckDecorator {

    public OrangeDuck(final Duck duck) {
	super(duck);
    }

    @Override
    public int getScoreMultiplier() {
	return DuckProperty.ORANGE_DUCK.getMultiplierScore();
    }
}
