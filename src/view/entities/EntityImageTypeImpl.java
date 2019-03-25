package view.entities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import model.entities.Dog;
import model.entities.Entity;

/**
 *  EntityImage implementation.
 */
public final class EntityImageTypeImpl implements EntityImageType {

    private static final int UPDATE_IMAGE = 250;
    private int elapsed;
    private Dog dog;
    private int index;
    private final List<Image> dogRightImages;
    private final List<Image> dogSniffImages;
    private final List<Image> dogLaughImages;
    /**
     * Constructor of EntityImageTypeImpl.
     */
    public EntityImageTypeImpl() {
        super();
        this.elapsed = 0;
        this.index = 0;
        this.dogRightImages = new ArrayList<>();
        this.dogSniffImages = new ArrayList<>();
        this.dogLaughImages = new ArrayList<>();
        this.initializeDogImages();
    }

    private void initializeDogImages() {
        this.dogRightImages.add(DogType.DOG_RIGHT.getPicture());
        this.dogRightImages.add(DogType.DOG_RIGHT1.getPicture());
        this.dogRightImages.add(DogType.DOG_RIGHT2.getPicture());
        this.dogSniffImages.add(DogType.DOG_SNIFF.getPicture());
        this.dogSniffImages.add(DogType.DOG_SNIFF1.getPicture());
        this.dogLaughImages.add(DogType.DOG_LAUGH.getPicture());
        this.dogLaughImages.add(DogType.DOG_LAUGH1.getPicture());
    }

    @Override
    public Image getImageType(final Entity entity) {
        Image image = null;
        if (entity instanceof Dog) {
            switch (dog.getDogStatus()) {
                case ATTENTION:
                    image = DogType.DOG_ATTENTION.getPicture();
                    break;
                case RIGHT:
                    this.updateIndex();
                    if (index >= this.dogRightImages.size()) {
                        this.index = 0;
                    }
                    image = this.dogRightImages.get(this.index);
                    break;
                case SNIFF:
                    this.updateIndex();
                    if (index >= this.dogSniffImages.size()) {
                        this.index = 0;
                    }
                    image = this.dogSniffImages.get(this.index);
                    break;
                case JUMP:
                    image = DogType.DOG_JUMP.getPicture();
                    break;
                case LAUGH:
                    this.updateIndex();
                    if (index >= this.dogLaughImages.size()) {
                        this.index = 0;
                    }
                    image = this.dogLaughImages.get(this.index);
                    break;
                case HAPPY:
                    break;
                default:
                    break;
            }
        }
        return image;
    }

    private void updateIndex() {
        if (this.elapsed >= UPDATE_IMAGE) {
            this.index++;
            this.elapsed -= UPDATE_IMAGE;
        }
    }

    @Override
    public void updateEntity(final Entity entity, final int elapsed) {
        this.elapsed += elapsed;
        if (entity instanceof Dog) {
            this.dog = (Dog) entity;
        }
    }
}
