package settings;

/**
 *
 * the FPS of the game that can be choose from the settings.
 */
public enum SettingsFPS {

    /**
     * low FPS.
     */
    LOW("20"),
    /**
     * standard FPS.
     */
    MEDIUM("30"),
    /**
     * high FPS.
     */
    HIGH("60");

    private String fps;

    SettingsFPS(final String value) {
        this.fps = value;
    }

    /**
     * method for return FPS.
     *
     * @return
     *  the frames per second
     */
    public String getFPS() {
        return this.fps;
    }

}
