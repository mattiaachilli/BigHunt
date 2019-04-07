package view.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.image.Image;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.decorator.YellowDuck;
import model.entities.Dog;
import model.entities.DogStatus;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.StandardDuck;

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
    public final Optional<Image> getImage() {
        Optional<Image> image = Optional.empty();
        switch (dog.getDogStatus()) {
            case ATTENTION:
                image = Optional.of(DogType.DOG_ATTENTION.getPicture());
                break;
            case RIGHT:
                if (this.index >= this.dogRightImages.size()) {
                    this.index = 0;
                }
                image = Optional.of(this.dogRightImages.get(this.index));
                break;
            case SNIFF:
                if (this.index >= this.dogSniffImages.size()) {
                    this.index = 0;
                }
                image = Optional.of(this.dogSniffImages.get(this.index));
                break;
            case JUMP:
                if (this.index >= this.dogJumpImages.size()) {
                    this.index = 1;
                }
                image = Optional.of(this.dogJumpImages.get(this.index));
                break;
            case LAUGH:
                if (this.index >= this.dogLaughImages.size()) {
                    this.index = 0;
                }
                image = Optional.of(this.dogLaughImages.get(this.index));
                break;
            case HAPPY:
                final Optional<Duck> duck = this.dog.getLastDuckKilled();
                if (duck.isPresent()) {
                    if (duck.get() instanceof StandardDuck) {
                        image = Optional.of(DogType.DOG_HAPPY_STANDARD.getPicture());
                    } else if (duck.get() instanceof YellowDuck) {
                        image = Optional.of(DogType.DOG_HAPPY_YELLOW.getPicture());
                    } else if (duck.get() instanceof OrangeDuck) {
                        image = Optional.of(DogType.DOG_HAPPY_ORANGE.getPicture());
                    } else if (duck.get() instanceof PinkDuck) {
                        image = Optional.of(DogType.DOG_HAPPY_PINK.getPicture());
                    }
                }
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
