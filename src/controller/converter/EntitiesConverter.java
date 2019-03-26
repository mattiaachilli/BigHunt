package controller.converter;

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
     * @return the entity for the view.
     */
    public static ViewEntity convertEntity(final Entity entity) {
        return new ViewEntityImpl(entity.getShape(), EntityImageTypeImpl.getInstance().getImageType(entity));
    }
}
