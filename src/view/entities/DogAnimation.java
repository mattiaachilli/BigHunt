package view.entities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import model.entities.Dog;

public class DogAnimation implements EntityImageAnimation {
    
    private int index;
    private Dog dog;
    private final List<Image> dogRightImages;
    private final List<Image> dogSniffImages;
    private final List<Image> dogLaughImages;

    /**
     * Constructor of dog animation
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
        this.dogLaughImages.add(DogType.DOG_LAUGH.getPicture());
        this.dogLaughImages.add(DogType.DOG_LAUGH1.getPicture());
    }
    
    @Override
    public final Image getImage() {
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
            System.out.println("Right, indice: " + this.index);
            break;
        case SNIFF:
            this.updateIndex();
            if (index >= this.dogSniffImages.size()) {
                this.index = 0;
            }
            image = this.dogSniffImages.get(this.index);
            System.out.println("Sniff, indice: " + this.index);
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
            System.out.println("Laugh, indice: " + this.index);
            break;
        case HAPPY:
            break;
        default:
            break;
    }

}

    @Override
    public void update(int elapsed) {
        // TODO Auto-generated method stub
        
    }
