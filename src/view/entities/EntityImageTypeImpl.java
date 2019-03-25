package view.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.image.Image;
import model.entities.Dog;
import model.entities.Entity;

/**
 *  EntityImage implementation.
 */
public final class EntityImageTypeImpl implements EntityImageType {

    private static final int UPDATE_IMAGE = 250;
    private int elapsed;
    private final List<EntityImageAnimation> entitiesAnimation;

    private static EntityImageTypeImpl singleton;

    private EntityImageTypeImpl() {
        super();
        this.entitiesAnimation = new ArrayList<>();
    }

    /**
     * 
     * @return the instance of this object, once.
     */
    public static EntityImageTypeImpl getInstance() {
        if (Objects.isNull(singleton)) {
            singleton = new EntityImageTypeImpl();
        }
        return singleton;
    }

    @Override
    public Image getImageType(final Entity entity) {
        Image image = null;
        if (entity instanceof Dog) {
            
        }
        return image;
    }

    private void updateIndex() {
        if (this.elapsed >= UPDATE_IMAGE) {
            this.elapsed -= UPDATE_IMAGE;
        }
    }

    @Override
    public void updateEntity(final Entity entity, final int elapsed) {
        this.elapsed += elapsed;
        if (entity instanceof Dog) {
            
        }
    }
}
