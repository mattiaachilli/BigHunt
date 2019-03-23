package view.entities;

import javafx.scene.image.Image;

/**
 * 
 *
 */
public interface EntityImageType {
    
    /**
     * Add time for animation.
     * 
     * @param elapsed
     *          from the last render of image.
     */
    void addTimeAnimation(int elapsed);
    /**
     * Get entity type based on input entity.
     * 
     * @return the entity type
     */
    Image getImageType();
}
