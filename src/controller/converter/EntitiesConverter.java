package controller.converter;

import javafx.scene.image.Image;
import java.util.Optional;

import model.entities.Entity;
import view.entities.EntityImageTypeImpl;
import view.entities.ViewEntity;
import view.entities.ViewEntityImpl;

/**
 * 
 * This class converts entities from model for the view, give Image, Position, Shape.
 *
 */
public final class EntitiesConverter {

    private EntitiesConverter() {
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
    public static ViewEntity convertEntity(final Entity entity, final int elapsed) {
        EntityImageTypeImpl.getInstance().updateEntity(entity, elapsed);
        Optional<Image> image = Optional.ofNullable(EntityImageTypeImpl.getInstance().getImageType(entity).get());
        return new ViewEntityImpl(entity.getShape(), image.isPresent() ? image.get() : null);
    }
}
