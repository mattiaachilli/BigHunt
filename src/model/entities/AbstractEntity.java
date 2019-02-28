package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Position;
import model.properties.Velocity;
import model.utilities.ShapeUtility;

/**
 * 
 * AbstractEntity class that represents a entity that have only shape, position and velocity.
 *
 */
public class AbstractEntity implements Entity {
    
    private static final double UPDATE_SECONDS = 0.001;
    
    private Shape shape;
    private Velocity velocity;

    
    public AbstractEntity(final Shape shape, final Velocity velocity) {
	this.shape = shape;
	this.velocity = velocity;
    }

    @Override
    public Position getPosition() {
	return ShapeUtility.getPositionbyShape(this.shape);
    }

    @Override
    public void setPosition(Position position) {
	ShapeUtility.setShapePosition(this.shape, position);
    }

    @Override
    public Velocity getVelocity() {
	return this.velocity;
    }

    @Override
    public void setVelocity(Velocity velocity) {
	this.velocity = velocity;
    }

    @Override
    public Shape getShape() {
	return this.shape;
    }

    @Override
    public void setShape(Shape shape) {
	this.shape = shape;
    }

    @Override
    public void update(int timeElapsed) {
	// TODO Auto-generated method stub

    }
}
