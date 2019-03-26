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
    private DogStatus lastDogStatus;
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
        this.dogJumpImages = new ArrayList<>();
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


    private void updateIndex() {
        if (this.elapsed >= EntityImageTypeImpl.UPDATE_IMAGE) {
            this.elapsed -= EntityImageTypeImpl.UPDATE_IMAGE;
            this.index++;
        }
    }

    @Override
    public final Image getImage() {
        Image image = null;
        switch (dog.getDogStatus()) {
            case ATTENTION:
                image = DogType.DOG_ATTENTION.getPicture();
                break;
            case RIGHT:
                if (this.index >= this.dogRightImages.size()) {
                    this.index = 0;
                }
                image = this.dogRightImages.get(this.index);
                System.out.println("Right, indice: " + this.index);
                break;
            case SNIFF:
                if (this.index >= this.dogSniffImages.size()) {
                    this.index = 0;
                }
                image = this.dogSniffImages.get(this.index);
                System.out.println("Sniff, indice: " + this.index);
                break;
            case JUMP:
                if (this.index >= this.dogJumpImages.size()) {
                    this.index = 1;
                }
                image = this.dogJumpImages.get(this.index);
                System.out.println("Jump, indice: " + this.index);
                break;
            case LAUGH:
                if (this.index >= this.dogLaughImages.size()) {
                    this.index = 0;
                }
                image = this.dogLaughImages.get(this.index);
                System.out.println("Laugh, indice: " + this.index);
                break;
            case HAPPY:
                break;
            default:
                break;
        }
        this.updateIndex();
        return image;
    }

    @Override
    public final void update(final Entity entity, final int elapsed) {
        this.dog = (Dog) entity;
        this.elapsed += elapsed;
        if (this.lastDogStatus != this.dog.getDogStatus()) {
            this.index = 0;
        }
        this.lastDogStatus = this.dog.getDogStatus();
    }
}
