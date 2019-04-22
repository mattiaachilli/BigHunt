package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Position;
import model.properties.Velocity;

/**
 * 
 * Represents an entity in game that has a position, a velocity and a shape.
 *
 */
public interface Entity {

    /**
     * Gets the position.
     * 
     * @return entity's position
     */
    Position getPosition();

    /**
     * Sets a new position.
     * 
     * @param position
     *          new entity's position
     * 
     */
    void setPosition(Position position);

    /**
     * Gets velocity.
     * 
     * @return entity's velocity
     */
    Velocity getVelocity();

    /**
     * Sets new velocity.
     * 
     * @param velocity
     *          new entity's velocity
     */
    void setVelocity(Velocity velocity);

    /**
     * Gets shape.
     * 
     * @return entity's shape
     */
    Shape getShape();

    /**
     * Sets new shape.
     * 
     * @param shape
     *          new entity's shape
     */
    void setShape(Shape shape);

    /**
     * Updates the entity's state.
     * 
     * @param timeElapsed 
     *          is the time elapsed from the last update
     */
    void update(int timeElapsed);
}
