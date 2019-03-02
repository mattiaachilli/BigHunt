package model.decorator;

import model.entities.Duck;
import model.entities.DuckProperty;
import model.properties.Velocity;

public class OrangeDuck extends DuckDecorator {

    public OrangeDuck(final Duck duck) {
	super(duck);
    }

    @Override
    public int getScoreMultiplier() {
	return DuckProperty.ORANGE_DUCK.getMultiplierScore();
    }
    
    @Override
    public void setNewVelocity() {
	final Velocity newVelocity = this.getVelocity().mul(DuckProperty.ORANGE_DUCK.getVelocitySpeedUp());
	this.setVelocity(newVelocity);
    }
}
