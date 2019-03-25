package view.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;
import model.entities.Entity;
import model.properties.Position;

/**
 * Interface that represents entity in view.
 *
 */
public interface EntityImageType {
    /**
     * 
     * @return entity's position
     */
    Position getPosition();

    /**
     * 
     * @return the entity's shape
     */
    Shape getShape();

    /**
     * Get entity type based on input entity.
     * 
     * @param entity
     *          entity to get image
     * 
     * @return the entity type
     */
    Image getImageType(Entity entity);

    /**
     * Update entity.
     * 
     * @param entity
     *          update entity
     * @param elapsed
     *          elapsed from the last update
     */
    void updateEntity(Entity entity, int elapsed);
}
