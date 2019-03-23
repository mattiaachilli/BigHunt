package view.entities;

import javafx.scene.image.Image;
import model.entities.Dog;
import model.entities.Entity;

/**
 * 
 *
 */
public final class EntityImageTypeImpl implements EntityImageType {

    private final Entity entity;

    /**
     * Constructor of EntityTypeImpl.
     * 
     * @param entity
     *          to convert.
     */
    public EntityImageTypeImpl(final Entity entity) {
        this.entity = entity;
    }

    @Override
    public Image getImageType() {
        Image image = null;
        if (this.entity instanceof Dog) {
            final Dog dog = (Dog) this.entity;
            switch (dog.getDogStatus()) {
                case ATTENTION:
                    image = DogType.DOG_ATTENTION.getPicture();
                    break;
                case RIGHT:
                    image = DogType.DOG_RIGHT.getPicture();
                    break;
                case SNIFF:
                    image = DogType.DOG_SNIFF.getPicture();
                    break;
                case JUMP:
                    image = DogType.DOG_JUMP.getPicture();
                    break;
                case LAUGH:
                    image = DogType.DOG_LAUGH.getPicture();
                    break;
                case HAPPY:
                    break;
                default:
                    break;
            }
        }
        return image;
    }

    @Override
    public void addTimeAnimation(int elapsed) {
        // TODO Auto-generated method stub
        
    }
}
