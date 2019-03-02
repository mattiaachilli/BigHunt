package model.decorator;

import java.util.Optional;

import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.Health;
import model.entities.PowerUp;
import model.properties.Velocity;
import model.properties.VelocityImpl;

public class GreenDuck extends DuckDecorator {

    public GreenDuck(final Duck duck) {
	super(duck);
    }

    @Override
    public int getScoreMultiplier() {
	return DuckProperty.GREEN_DUCK.getMultiplierScore();
    }
    
    @Override
    public void setNewVelocity() {
	final Velocity newVelocity = this.getVelocity().mul(DuckProperty.GREEN_DUCK.getVelocitySpeedUp());
	this.setVelocity(newVelocity);
    }
}
