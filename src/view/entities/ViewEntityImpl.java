package view.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;
import model.properties.Position;
import model.utilities.ShapeUtility;

/**
 * Implementation of ViewEntity.
 */
public final class ViewEntityImpl implements ViewEntity {
    private final Shape shape;
    private final Image image;

    /**
     * Constructor of ViewEntityImpl.
     * 
     * @param shape
     *          of the entity.
     * @param image
     *          of the entity.
     */
    public ViewEntityImpl(final Shape shape, final Image image) {
        this.shape = ShapeUtility.getShapeCopy(shape).get();
        this.image = image;
    }

    @Override
    public Position getPosition() {
        return ShapeUtility.getPositionbyShape(this.shape);
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public Image getPicture() {
        return this.image;
    }
}
