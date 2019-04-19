package view.entities;

import java.util.Optional;

import javafx.scene.image.Image;
import model.entities.Entity;

/**
 * 
 * Represents the image of this entity, its animation.
 *
 */
public interface EntityImageAnimation {

    /**
     * Gets the current image of this entity.
     * 
     * @return the image
     */
    Optional<Image> getImage();

    /**
     * Adds elapsed.
     * 
     * @param entity
     *          entity to update.
     * @param elapsed
     *          elapsed from the last update.
     */
    void update(Entity entity, int elapsed);
}
