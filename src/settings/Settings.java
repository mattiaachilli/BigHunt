package settings;


import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * the interface to manage the game settings.
 *
 */
public interface Settings {
    /**
    *
    * @return
    *          default screen resolution.
    */
   Pair<Integer, Integer> getDefaultResolution();
    /**
     *
     * @return the current game resolution
     */
    Pair<Integer, Integer> getSelectedResolution();
    /**
     * Set the game FPS.
     *
     * @param selectedFPS
     *          the selected FPS
     */
    void setSelectedFPS(String selectedFPS);
    /**
     *
     * @return the current FPS
     */
    SettingsFPS getSelectedFPS();
    /**
     * Provides the factor by which coordinates are scaled about the center of the object.
     *
     * @return the scale factor
     */
    double getScaleFactor();
    /**
     *
     * @return true if fullScreenMode is enable, false otherwise
     */
    boolean isFullScreen();
    /**
     * Change the resolution to Full Screen.
     *
     * @param fullScreen
     *          is true if is full screen active, false otherwise
     */
    void setFullScreen(boolean fullScreen);
    /**
     *
     * @return true if the background audio is on, false otherwise
     */
    boolean isBackgroundAudioOn();
    /**
     * Change the background audio to status on to off.
     *
     * @param backgroundAudio
     *          is true if background audio is active, false otherwise
     */
    void setBackgroundAudio(boolean backgroundAudio);
    /**
     *
     * @param difficulty
     *          the selected game difficulty.
     */
    void setDifficulty(String difficulty);
    /**
     *
     * @return
     *          the actual game difficulty.
     */
    GlobalDifficulty getSelectedDifficulty();
}
