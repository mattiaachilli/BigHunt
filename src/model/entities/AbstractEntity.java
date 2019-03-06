package model.entities;

import javafx.scene.shape.Shape;
import model.properties.*;
import model.utilities.ShapeUtility;

/**
 * 
 * AbstractEntity class that represents a entity that have only shape, position and velocity.
 *
 */
public class AbstractEntity implements Entity {
    
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
    public void setPosition(final Position position) {
	ShapeUtility.setShapePosition(this.shape, position);
    }

    @Override
    public Velocity getVelocity() {
	return this.velocity;
    }

    @Override
    public void setVelocity(final Velocity velocity) {
	this.velocity = velocity;
    }

    @Override
    public Shape getShape() {
	return this.shape;
    }

    @Override
    public void setShape(final Shape shape) {
	this.shape = shape;
    }

    @Override
    public void update(final int timeElapsed) {
	final Velocity velocity = this.getVelocity().mul(timeElapsed);
	final Position shapePosition = this.getPosition();
	this.setPosition(new PositionImpl(shapePosition.getX() + velocity.getX(), 
		shapePosition.getY() + velocity.getY()));
    }
}
