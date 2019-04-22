package settings;

/**
 *
 * the audio that can be activated from the game setting.
 *
 */
public enum SettingsAudio {

    /**
     * text to display when audio in on.
     */
    AUDIO_ON("ON"),

    /**
     * text to display when audio is off.
     */
    AUDIO_OFF("OFF");

    private final String text;

    SettingsAudio(final String text) {
        this.text = text;
    }

    /**
     *
     * @return
     *          the current status of audio game.
     */
    public String getText() {
        return this.text;
    }
}
