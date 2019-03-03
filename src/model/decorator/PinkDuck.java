package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;
import model.properties.Velocity;

public final class PinkDuck extends DuckDecorator {
  
    public PinkDuck(final Duck duck) {
	super(duck);
    }

    @Override
    public int getScoreMultiplier() {
	return DuckProperty.PINK_DUCK.getMultiplierScore();
    }
    
    @Override
    public void setNewVelocity() {
	final Velocity newVelocity = this.getVelocity().mul(DuckProperty.PINK_DUCK.getVelocitySpeedUp());
	this.setVelocity(newVelocity);
    }
}
