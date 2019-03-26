package view.entities;

import java.util.Objects;

import javafx.scene.image.Image;
import model.entities.Dog;
import model.entities.Duck;
import model.entities.Entity;

/**
 *  EntityImage implementation.
 */
public final class EntityImageTypeImpl implements EntityImageType {

    /**
     * MILLIS UPDATE FRAME.
     */
    protected static final int UPDATE_IMAGE = 250;
    private final EntityImageAnimation dogAnimation;
    private final EntityImageAnimation duckAnimation;

    private static EntityImageTypeImpl singleton;

    private EntityImageTypeImpl() {
        super();
        this.dogAnimation = new DogAnimation();
        this.duckAnimation = new DuckAnimation();
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
            image = this.dogAnimation.getImage();
        } else if (entity instanceof Duck) {
            image = this.duckAnimation.getImage();
        }
        return image;
    }

    @Override
    public void updateEntity(final Entity entity, final int elapsed) {
        if (entity instanceof Dog) {
            this.dogAnimation.update(entity, elapsed);
        } else if (entity instanceof Duck) {
            this.duckAnimation.update(entity, elapsed);
        }
    }
}
