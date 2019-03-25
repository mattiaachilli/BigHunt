package view.entities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import model.entities.Dog;
import model.entities.DogStatus;
import model.entities.Entity;

/**
 * 
 * Animation of the dog.
 *
 */
public class DogAnimation implements EntityImageAnimation {

    private int index;
    private int elapsed;
    private Dog dog;
    private final List<Image> dogRightImages;
    private final List<Image> dogSniffImages;
    private final List<Image> dogJumpImages;
    private final List<Image> dogLaughImages;

    /**
     * Constructor of dog animation.
     */
    public DogAnimation() {
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
        this.dogJumpImages.add(DogType.DOG_JUMP.getPicture());
        this.dogJumpImages.add(DogType.DOG_LANDING.getPicture());
        this.dogLaughImages.add(DogType.DOG_LAUGH.getPicture());
        this.dogLaughImages.add(DogType.DOG_LAUGH1.getPicture());
    }


    private void updateIndex(final DogStatus dogStatus) {
        if (this.elapsed >= EntityImageTypeImpl.UPDATE_IMAGE) {
            this.elapsed -= EntityImageTypeImpl.UPDATE_IMAGE;
            this.index++;
            switch (dogStatus) {
                case RIGHT:
                    this.index = this.index >= this.dogRightImages.size() ? 0 : this.index;
                    break;
                case SNIFF:
                    this.index = this.index >= this.dogSniffImages.size() ? 0 : this.index;
                    break;
                case LAUGH:
                    this.index = this.index >= this.dogLaughImages.size() ? 0 : this.index;
                    break;
                case JUMP:
                    this.index = 1;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public final Image getImage() {
        Image image = null;
        this.updateIndex(dog.getDogStatus());
        switch (dog.getDogStatus()) {
            case ATTENTION:
                image = DogType.DOG_ATTENTION.getPicture();
                break;
            case RIGHT:
                image = this.dogRightImages.get(this.index);
                System.out.println("Right, indice: " + this.index);
                break;
            case SNIFF:
                image = this.dogSniffImages.get(this.index);
                System.out.println("Sniff, indice: " + this.index);
                break;
            case JUMP:
                image = this.dogJumpImages.get(this.index);
                System.out.println("Jump, indice: " + this.index);
                break;
            case LAUGH:
                image = this.dogLaughImages.get(this.index);
                //System.out.println("Laugh, indice: " + this.index);
                break;
            case HAPPY:
                break;
            default:
                break;
        }
        return image;
    }

    @Override
    public final void update(final Entity entity, final int elapsed) {
        this.dog = (Dog) entity;
        this.elapsed += elapsed;
    }
}
