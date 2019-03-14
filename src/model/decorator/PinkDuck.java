package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;

public final class PinkDuck extends DuckDecorator {
  
    public PinkDuck(final Duck duck) {
	super(duck);
    }

    @Override
    public int getScoreMultiplier() {
	return DuckProperty.PINK_DUCK.getMultiplierScore();
    }
}
