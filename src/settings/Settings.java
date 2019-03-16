package settings;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 
 * the interface to manage the game settings.
 * 
 */
public interface Settings {

    /**
     * Set the game resolution.
     * 
     * @param selectedResolution
     *                  the selected resolution
     */
    void setSelectedResolution(Pair<Integer, Integer> selectedResolution);

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
    void setSelectedFPS(int selectedFPS);

    /**
     * 
     * @return the current FPS
     */
    int getSelectedFPS();

    /**
     * Provides the factor by which coordinates are scaled about the center of the object.
     * 
     * @return the scale factor
     */
    double getScaleFactory();

    /**
     * 
     * @return a Set of default FPS
     */
    Set<Integer> getSupportedFPS();

    /**
     * Provides all the supported resolutions.
     * 
     * @return a list of supported resolutions
     */
    List<Pair<Integer, Integer>> getSupportedResolutions();

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
     * @return true if the background audio is active, false otherwise
     */
    boolean isBackgroundAudioActivated();

    /**
     * Change the background audio to status on to off.
     * 
     * @param backgroundAudio 
     *          is true if background audio is active, false otherwise
     */
    void setBackgroundAudio(boolean backgroundAudio);
}
