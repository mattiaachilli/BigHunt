package settings;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * Implementation of the settings interface.
 *
 */
public final class SettingsImpl implements Settings {

    private static final int DEFAULT_X_RESOLUTION = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int DEFAULT_Y_RESOLUTION = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final Pair<Integer, Integer> DEFAULT_RESOLUTION = new ImmutablePair<>(DEFAULT_X_RESOLUTION,
        DEFAULT_Y_RESOLUTION);

    private static final int PREF_X_RESOLUTION = 1366;
    private static final int PREF_Y_RESOLUTION = 768;

    private static final Pair<Integer, Integer> SCREEN_RESOLUTION = DEFAULT_RESOLUTION;

    private final Pair<Integer, Integer> selectedResolution = new ImmutablePair<>(
        SettingsImpl.SCREEN_RESOLUTION.getKey(), SettingsImpl.SCREEN_RESOLUTION.getValue());

    private static final int DEFAULT_FPS = 60;

    private final Set<Integer> supportedFPS = new TreeSet<>(Arrays.asList(20, 30, 60, GraphicsEnvironment
        .getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate()));

    private int selectedFPS = DEFAULT_FPS;

    private static final GlobalDifficulty DEFAULT_DIFFICULTY = GlobalDifficulty.MEDIUM;
    private GlobalDifficulty selectedDifficulty = DEFAULT_DIFFICULTY;

    private boolean fullScreen = true;
    private boolean backgroundAudio = true;

    private static SettingsImpl singleton;


    /**
     *
     * @return the instance of this object, once.
     */
    public static SettingsImpl getSettings() {
        if (Objects.isNull(singleton)) {
            singleton = new SettingsImpl();
        }
        return singleton;
    }

    @Override
    public Pair<Integer, Integer> getSelectedResolution() {
        return this.selectedResolution;
    }

    @Override
    public void setSelectedFPS(final int selectedFPS) {
        this.selectedFPS = selectedFPS;
    }

    @Override
    public int getSelectedFPS() {
        return this.selectedFPS;
    }

    @Override
    public double getScaleFactor() {
        return Math.min((double) this.selectedResolution.getValue() / SettingsImpl.PREF_Y_RESOLUTION,
            (double) this.selectedResolution.getKey() / SettingsImpl.PREF_X_RESOLUTION);
    }

    @Override
    public Set<Integer> getSupportedFPS() {
        return this.supportedFPS;
    }

    @Override
    public void setFullScreen(final boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    @Override
    public boolean isFullScreen() {
        return this.fullScreen;
    }

    @Override
    public boolean isBackgroundAudioOn() {
        return this.backgroundAudio;
    }

    @Override
    public void setBackgroundAudio(final boolean backgroundAudio) {
        this.backgroundAudio = backgroundAudio;
    }

    @Override
    public void setDifficulty(final String difficulty) {
        switch (difficulty) {
            case "EASY":
                this.selectedDifficulty = GlobalDifficulty.EASY;
                break;
            case "MEDIUM":
                this.selectedDifficulty = GlobalDifficulty.MEDIUM;
                break;
            case "HARD":
                this.selectedDifficulty = GlobalDifficulty.HARD;
                break;
            default:
                this.selectedDifficulty = DEFAULT_DIFFICULTY;
                break;
        }
    }

    @Override
    public GlobalDifficulty getSelectedDifficulty() {
        return this.selectedDifficulty;
    }

    @Override
    public Pair<Integer, Integer> getDefaultResolutions() {
        return SettingsImpl.DEFAULT_RESOLUTION;
    }

}
