package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Position;
import model.properties.Velocity;
import model.utilities.ShapeUtil;

/**
 * 
 * AbstractEntity class that represents a entity that have only shape, position and velocity.
 *
 */
public class AbstractEntity implements Entity {
    private static final double MILLISECOND_TO_SECOND = 0.001;
    private Shape shape;
    private Velocity velocity;


    /**
     * Constructor of abstract entity.
     * 
     * @param shape
     *          shape of the entity
     * @param velocity
     *          velocity of the entity
     */
    public AbstractEntity(final Shape shape, final Velocity velocity) {
        this.shape = shape;
        this.velocity = velocity;
    }

    @Override
    public final Position getPosition() {
        return ShapeUtil.getPositionbyShape(this.shape);
    }

    @Override
    public final void setPosition(final Position position) {
        ShapeUtil.setShapePosition(this.shape, position);
    }

    @Override
    public final Velocity getVelocity() {
        return this.velocity;
    }

    @Override
    public final void setVelocity(final Velocity velocity) {
        this.velocity = velocity;
    }

    @Override
    public final Shape getShape() {
        return this.shape;
    }

    @Override
    public final void setShape(final Shape shape) {
        this.shape = shape;
    }

    /**
     * Updates an entity.
     * 
     * @param timeElapsed
     *          timeElapsed from last update
     */
    @Override
    public void update(final int timeElapsed) {
        final Velocity velocity = this.getVelocity().mul(MILLISECOND_TO_SECOND * timeElapsed);
        this.setPosition(this.getPosition().sum(velocity));
    }
}
