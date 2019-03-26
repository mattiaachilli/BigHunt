package view.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;
import model.properties.Position;

/**
 * Represent the entity's information to draw it in view.
 */
public interface ViewEntity {

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
     * 
     * @return the entity's picture
     */
    Image getPicture();
}
