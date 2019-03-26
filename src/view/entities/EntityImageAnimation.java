package view.entities;

import java.util.Optional;

import javafx.scene.image.Image;
import model.entities.Entity;

/**
 * 
 * Represents the image of this entity, his animation.
 *
 */
public interface EntityImageAnimation {

    /**
     * Get the current image of this entity.
     * 
     * @return the image
     */
    Optional<Image> getImage();

    /**
     * Add elapsed.
     * 
     * @param entity
     *          entity to update.
     * @param elapsed
     *          elapsed from the last update.
     */
    void update(Entity entity, int elapsed);
}
