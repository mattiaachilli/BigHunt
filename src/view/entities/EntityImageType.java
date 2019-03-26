package view.entities;

import javafx.scene.image.Image;
import model.entities.Entity;

/**
 * Interface that represents entity in view.
 *
 */
public interface EntityImageType {

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
