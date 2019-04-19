package view.entities;

import java.util.Optional;

import javafx.scene.image.Image;
import model.entities.Entity;

/**
 * Interface that represents an entity in view.
 *
 */
public interface EntityImageType {

    /**
     * Gets the entity's type based on the input entity.
     * 
     * @param entity
     *          entity to get image
     * 
     * @return the entity type
     */
    Optional<Image> getImageType(Entity entity);

    /**
     * Updates the entity.
     * 
     * @param entity
     *          update entity
     * @param elapsed
     *          elapsed from the last update
     */
    void updateEntity(Entity entity, int elapsed);
}
