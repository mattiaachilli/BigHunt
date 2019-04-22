package view.entities;

import javafx.scene.image.Image;

/**
 * Enumeration used for powerUp's images.
 */
public enum PowerUpImages {
    /**
     * All types of powerUp images.
     */

    /**
     * DOUBLE SCORE.
     */
    DOUBLE_SCORE("greenStar.png"),

    /**
     * INFINITE AMMO.
     */
    INFITE_AMMO("yellowStar.png"),

    /**
     * SLOW DOWN.
     */
    SLOW_DOWN("redStar.png"),

    /**
     * KILL ALL.
     */
    KILL_ALL("blackStar.png");

    private static final String PATH = "/view/powerup/";
    private Image picture;

    /**
     * 
     * @param imageName,
     *            name of the image stored in resources
     */
    PowerUpImages(final String imageName) {
        this.picture = new Image(getClass().getResourceAsStream(PATH + imageName));
    }

    /**
     * 
     * @return the powerUp's picture
     */
    public Image getPicture() {
        return this.picture;
    }
}

