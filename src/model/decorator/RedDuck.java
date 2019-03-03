package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;
import model.properties.Velocity;

public final class RedDuck extends DuckDecorator {
  
    public RedDuck(final Duck duck) {
	super(duck);
    }

    @Override
    public int getScoreMultiplier() {
	return DuckProperty.RED_DUCK.getMultiplierScore();
    }
    
    @Override
    public void setNewVelocity() {
	final Velocity newVelocity = this.getVelocity().mul(DuckProperty.RED_DUCK.getVelocitySpeedUp());
	this.setVelocity(newVelocity);
    }
}
