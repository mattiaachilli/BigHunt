package view.entities;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.image.Image;

/**
 * Enumeration used to pink duck images.
 */
public enum PinkDuckImageType {
    /**
     * All types of yellow ducks images.
     */

    /**
     * DUCK RIGHT.
     */
    DUCK_RIGHT("pinkDuckRight0.png"),

    /**
     * DUCK RIGHT.
     */
    DUCK_RIGHT1("pinkDuckRight1.png"),

    /**
     * DUCK LEFT.
     */
    DUCK_LEFT("pinkDuckLeft0.png"),

    /**
     * DUCK LEFT.
     */
    DUCK_LEFT1("pinkDuckLeft1.png"),

    /**
     * DUCK UP RIGHT.
     */
    DUCK_UP_RIGHT("pinkDuckUpRight0.png"),

    /**
     * DUCK UP RIGHT.
     */
    DUCK_UP_RIGHT1("pinkDuckUpRight1.png"),

    /**
     * DUCK UP LEFT.
     */
    DUCK_UP_LEFT("pinkDuckUpLeft0.png"),

    /**
     * DUCK UP LEFT.
     */
    DUCK_UP_LEFT1("pinkDuckUpLeft1.png"),

    /**
     * DUCK DEAD.
     */
    DUCK_DEAD("pinkDuckDead.png"),

    /**
     * DUCK PRECIPITATE.
     */
    DUCK_PRECIPITATE("pinkDuckPrecipitate1.png"),

    /**
     * DUCK PRECIPITATE.
     */
    DUCK_PRECIPITATE1("pinkDuckPrecipitate3.png"),

    /**
     * DUCK FLY AWAY.
     */
    DUCK_FLY_AWAY("pinkDuckFlyAway0.png"),

    /**
     * DUCK FLY AWAY.
     */
    DUCK_FLY_AWAY1("pinkDuckFlyAway1.png");

    private static final String PATH = "/view/duck/pink/";
    private Image picture;

    /**
     * 
     * @param imageName,
     *            name of the image stored in resources
     */
    PinkDuckImageType(final String imageName) {
        this.picture = new Image(getClass().getResourceAsStream(PATH + imageName));
    }

    /**
     * 
     * @return right images.
     */
    public static List<Image> getRight() {
        return Stream.of(DUCK_RIGHT, DUCK_RIGHT1).map(d -> d.getPicture()).collect(Collectors.toList());
    }

    /**
     * 
     * @return up left images.
     */
    public static List<Image> getLeft() {
        return Stream.of(DUCK_LEFT, DUCK_LEFT1).map(d -> d.getPicture()).collect(Collectors.toList());
    }


    /**
     * 
     * @return up right images.
     */
    public static List<Image> getUpRight() {
        return Stream.of(DUCK_UP_RIGHT, DUCK_UP_RIGHT1).map(d -> d.getPicture()).collect(Collectors.toList());
    }

    /**
     * 
     * @return up left images.
     */
    public static List<Image> getUpLeft() {
        return Stream.of(DUCK_UP_LEFT, DUCK_UP_LEFT1).map(d -> d.getPicture()).collect(Collectors.toList());
    }

    /**
     * 
     * @return up left images.
     */
    public static List<Image> getPrecipitate() {
        return Stream.of(DUCK_PRECIPITATE, DUCK_PRECIPITATE1).map(d -> d.getPicture()).collect(Collectors.toList());
    }

    /**
     * 
     * @return up left images.
     */
    public static List<Image> getFlyAway() {
        return Stream.of(DUCK_FLY_AWAY, DUCK_FLY_AWAY1).map(d -> d.getPicture()).collect(Collectors.toList());
    }

    /**
     * 
     * @return the duck's picture
     */
    public Image getPicture() {
        return this.picture;
    }
}
