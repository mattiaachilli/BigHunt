package view.entities;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.image.Image;

/**
 * Enumeration used for orange duck images.
 */
public enum OrangeDuckImageType {
    /**
     * All types of orange ducks images.
     */

    /**
     * DUCK RIGHT.
     */
    DUCK_RIGHT("orangeDuckRight0.png"),

    /**
     * DUCK RIGHT.
     */
    DUCK_RIGHT1("orangeDuckRight1.png"),

    /**
     * DUCK LEFT.
     */
    DUCK_LEFT("orangeDuckLeft0.png"),

    /**
     * DUCK LEFT.
     */
    DUCK_LEFT1("orangeDuckLeft1.png"),

    /**
     * DUCK UP RIGHT.
     */
    DUCK_UP_RIGHT("orangeDuckUpRight0.png"),

    /**
     * DUCK UP RIGHT.
     */
    DUCK_UP_RIGHT1("orangeDuckUpRight1.png"),

    /**
     * DUCK UP LEFT.
     */
    DUCK_UP_LEFT("orangeDuckUpLeft0.png"),

    /**
     * DUCK UP LEFT.
     */
    DUCK_UP_LEFT1("orangeDuckUpLeft1.png"),

    /**
     * DUCK DEAD.
     */
    DUCK_DEAD("orangeDuckDead.png"),

    /**
     * DUCK PRECIPITATE.
     */
    DUCK_PRECIPITATE("orangeDuckPrecipitate1.png"),

    /**
     * DUCK FLY AWAY.
     */
    DUCK_FLY_AWAY("orangeDuckFlyAway0.png"),

    /**
     * DUCK FLY AWAY.
     */
    DUCK_FLY_AWAY1("orangeDuckFlyAway1.png");

    private static final String PATH = "/view/duck/orange/";
    private Image picture;

    /**
     * 
     * @param imageName,
     *            name of the image stored in resources
     */
    OrangeDuckImageType(final String imageName) {
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
