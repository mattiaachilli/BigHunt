package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Position;
import model.properties.Velocity;

/**
 * 
 * Represents the entity in game, a entity have a position and a velocity and a shape.
 *
 */
public interface Entity {

    /**
     * Get position.
     * 
     * @return entity's position
     */
    Position getPosition();

    /**
     * Set new position.
     * 
     * @param position
     *          new entity's position
     * 
     */
    void setPosition(Position position);

    /**
     * Get velocity.
     * 
     * @return entity's velocity
     */
    Velocity getVelocity();

    /**
     * Set new velocity.
     * 
     * @param velocity
     *          new entity's velocity
     */
    void setVelocity(Velocity velocity);

    /**
     * Get shape.
     * 
     * @return entity's shape
     */
    Shape getShape();

    /**
     * Set new shape.
     * 
     * @param shape
     *          new entity's shape
     */
    void setShape(Shape shape);

    /**
     * Update entity's state.
     * 
     * @param timeElapsed 
     *          is the time elapsed from the last update
     */
    void update(int timeElapsed);
}
