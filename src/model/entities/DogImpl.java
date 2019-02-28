package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Velocity;

public final class DogImpl extends AbstractEntity implements Dog {
    
    private boolean inGrass;
    
    public DogImpl(final Shape shape, final Velocity velocity) {
	super(shape, velocity);
	this.inGrass = false;
    }
    
    public DogImpl() {
	//this();
    }

    @Override
    public boolean isInGrass() {
	return this.inGrass;
    }

    @Override
    public void inGrass() {
	this.inGrass = true;
    }
}
