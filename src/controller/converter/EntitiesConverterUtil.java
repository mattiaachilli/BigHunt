package controller.converter;

import javafx.scene.image.Image;
import java.util.Optional;

import model.entities.Entity;
import view.entities.EntityImageType;
import view.entities.EntityImageTypeImpl;
import view.entities.ViewEntity;
import view.entities.ViewEntityImpl;

/**
 * 
 * This class converts entities from model for the view, give Image, Position, Shape.
 *
 */
public final class EntitiesConverterUtil {
    private static EntityImageType e = new EntityImageTypeImpl();;

    private EntitiesConverterUtil() {
    }

    /**
     * Convert entity from model to view.
     * 
     * @param entity
     *          entity to convert.
     * @param elapsed
     *          elapsed from the last update, used for animation of entities.
     * @return the entity for the view.
     */
    public static Optional<ViewEntity> convertEntity(final Entity entity, final int elapsed) {
        e.updateEntity(entity, elapsed);
        final Optional<Image> image = e.getImageType(entity);
        return image.isPresent() ? Optional.of(new ViewEntityImpl(entity.getShape(), image.get())) : Optional.empty();
    }
}
