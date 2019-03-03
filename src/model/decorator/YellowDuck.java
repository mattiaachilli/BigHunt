package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;
import model.properties.Velocity;

public final class YellowDuck extends DuckDecorator {

    public YellowDuck(final Duck duck) {
	super(duck);
    }

    @Override
    public int getScoreMultiplier() {
	return DuckProperty.YELLOW_DUCK.getMultiplierScore();
    }
    
    @Override
    public void setNewVelocity() {
	final Velocity newVelocity = this.getVelocity().mul(DuckProperty.YELLOW_DUCK.getVelocitySpeedUp());
	this.setVelocity(newVelocity);
    }
}
