package view.entities;

import javafx.scene.image.Image;

/**
 * Enumeration used for dog images.
 */
public enum DogImageType {
    /**
     * All types of dog images.
     */
    /**
     * DOG ATTENTION.
     */
    DOG_ATTENTION("dogAttention.png"),
    /**
     * DOG JUMP.
     */
    DOG_JUMP("dogJump.png"),
    /**
     * DOG LANDING.
     */
    DOG_LANDING("dogLanding.png"),
    /**
     * DOG LAUGH.
     */
    DOG_LAUGH("dogLaugh0.png"),
    /**
     * DOG LAUGH.
     */
    DOG_LAUGH1("dogLaugh1.png"),
    /**
     * DOG RIGHT.
     */
    DOG_RIGHT("dogRight0.png"),
    /**
     * DOG RIGHT.
     */
    DOG_RIGHT1("dogRight1.png"),
    /**
     * DOG RIGHT.
     */
    DOG_RIGHT2("dogRight2.png"),
    /**
     * DOG SNIFF.
     */
    DOG_SNIFF("dogSniff0.png"),
    /**
     * DOG SNIFF.
     */
    DOG_SNIFF1("dogSniff1.png"),

    /**
     * DOG HAPPY, STANDARD DUCK.
     */
    DOG_HAPPY_STANDARD("dogHappy.png"),
    /**
     * DOG HAPPY, YELLOW DUCK.
     */
    DOG_HAPPY_YELLOW("yellowDogHappy.png"),
    /**
     * DOG HAPPY, ORANGE DUCK.
     */
    DOG_HAPPY_ORANGE("orangeDogHappy.png"),
    /**
     * DOG HAPPY, PINK DUCK.
     */
    DOG_HAPPY_PINK("pinkDogHappy.png");

    private static final String PATH = "/view/dog/";
    private Image picture;

    /**
     * 
     * @param imageName,
     *            name of the image stored in resources
     */
    DogImageType(final String imageName) {
        this.picture = new Image(getClass().getResourceAsStream(PATH + imageName));
    }

    /**
     * 
     * @return the dog's picture
     */
    public Image getPicture() {
        return this.picture;
    }
}
