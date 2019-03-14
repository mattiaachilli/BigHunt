package model.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javafx.scene.shape.Shape;
import model.ModelImpl;
import model.properties.*;
import model.utilities.ShapeUtility;

/**
 * 
 * AbstractEntity class that represents a entity that have only shape, position and velocity.
 *
 */
public class AbstractEntity implements Entity {
    private static final double MILLISECOND_TO_SECOND = 0.001;
    
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
	final Velocity velocity = this.getVelocity().mul(MILLISECOND_TO_SECOND * timeElapsed);
        this.setPosition(this.getPosition().sum(velocity));
    }

    /*
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.green);
        g.fill(new Rectangle2D.Double(this.getPosition().getX(), this.getPosition().getY(), 50, 50));
    }
    */
}
