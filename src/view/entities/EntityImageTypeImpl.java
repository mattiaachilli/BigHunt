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

    /**
     * MILLIS UPDATE FRAME.
     */
    protected static final int UPDATE_IMAGE = 250;
    private final EntityImageAnimation dogAnimation;

    private static EntityImageTypeImpl singleton;

    private EntityImageTypeImpl() {
        super();
        this.dogAnimation = new DogAnimation();
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
        }
        return image;
    }

    @Override
    public void updateEntity(final Entity entity, final int elapsed) {
        if (entity instanceof Dog) {
            this.dogAnimation.update(entity, elapsed);
        }
    }
}
