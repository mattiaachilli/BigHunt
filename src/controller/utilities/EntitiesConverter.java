package controller.utilities;

import model.entities.Entity;
import view.entities.EntityImageTypeImpl;
import view.entities.ViewEntity;
import view.entities.ViewEntityImpl;

/**
 * 
 * This static class convert entities from model for the view.
 *
 */
public final class EntitiesConverter {

    private EntitiesConverter() {
        super();
    }
    /**
     * Convert entities from model to view.
     * 
     * @param entity
     *          to convert.
     * @return the entity for view.
     */
    public static ViewEntity convertEntity(final Entity entity) {
        return new ViewEntityImpl(entity.getShape(), new EntityImageTypeImpl(entity).getImageType());
    }
}
