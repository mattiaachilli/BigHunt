package settings;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
    private static final Pair<Integer, Integer> PREF_RESOLUTION = new ImmutablePair<>(PREF_X_RESOLUTION,
        PREF_Y_RESOLUTION);

    private static final Pair<Integer, Integer> SCREEN_RESOLUTION = DEFAULT_RESOLUTION;

    private Pair<Integer, Integer> selectedResolution = new ImmutablePair<>(
        SettingsImpl.SCREEN_RESOLUTION.getKey(), SettingsImpl.SCREEN_RESOLUTION.getValue());

    private static final int DEFAULT_FPS = 60;

    private final Set<Integer> supportedFPS = new TreeSet<>(Arrays.asList(20, 30, 60, GraphicsEnvironment
        .getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate()));

    private int selectedFPS = DEFAULT_FPS;

    private static final String DEFAULT_DIFFICULTY = GlobalDifficulty.MEDIUM.getGlobalDifficulty();
    private String selectedDifficulty = DEFAULT_DIFFICULTY;
    private List<String> gameDifficulty = this.writeGameDifficulty();
    
    private boolean fullScreen = true;
    private boolean backgroundAudio = true;

    private static SettingsImpl singleton;

    private SettingsImpl() {
        super();
    }

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
    public double getScaleFactory() {
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
    public boolean isBackgroundAudioActivated() {
        return this.backgroundAudio;
    }

    @Override
    public void setBackgroundAudio(final boolean backgroundAudio) {
        this.backgroundAudio = backgroundAudio;
    }

    @Override
    public void setDifficulty(final String difficulty) {
        if(this.gameDifficulty.isEmpty()) {
            this.gameDifficulty = this.writeGameDifficulty();
        }
        if(!this.gameDifficulty.contains(difficulty)) {
            this.selectedDifficulty = DEFAULT_DIFFICULTY;
        } else {
            this.selectedDifficulty = difficulty; 
        }
    }

    @Override
    public String getSelectedDifficulty() {
        return this.selectedDifficulty;
    }

    @Override
    public List<String> getGameDifficulties() {
        return this.gameDifficulty;
    }
    
    private List<String> writeGameDifficulty() {
        
        final List<String> difficulty = new ArrayList<>();

        for (int i = 0; i < GlobalDifficulty.values().length; i++) {
            difficulty.add(GlobalDifficulty.values()[i].getGlobalDifficulty());
        }
        
        return difficulty;
    }

    @Override
    public Pair<Integer, Integer> getDefaultResolutions() {
        return SettingsImpl.DEFAULT_RESOLUTION;
    }
}
