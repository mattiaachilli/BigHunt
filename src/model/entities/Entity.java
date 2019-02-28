package model.entities;

import javafx.scene.shape.Shape;
import model.properties.Position;
import model.properties.Velocity;

/**
 * 
 * Represents the entity in game, a entity have a position and a velocity
 *
 */
public interface Entity {
    
    /**
     * 
     * @return entity's position
     */
    Position getPosition();
    
    /**
     * 
     * @param position, new entity's position
     * 
     */
    void setPosition(Position position);
    
    /**
     * 
     * @return entity's velocity
     */
    Velocity getVelocity();
    
    
    /**
     * 
     * @param velocity, new entity's velocity
     */
    void setVelocity(Velocity velocity);
    
    /**
     * 
     * @return entity's shape
     */
    Shape getShape();
    
    /**
     * 
     * @param shape, new entity's shape
     */
    void setShape(Shape shape);
    
    /**
     * Update entity's state
     * 
     * @param timeElapsed is the time elapsed from the last update
     */
    void update(int timeElapsed);
}
