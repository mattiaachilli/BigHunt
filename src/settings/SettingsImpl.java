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

    private static final int FIRST_SCREEN_PROPORTION = 3;
    private static final int LAST_SCREEN_PROPORTION = 5;

    private static final Pair<Integer, Integer> DEFAULT_RESOLUTION = new ImmutablePair<>(DEFAULT_X_RESOLUTION,
        DEFAULT_Y_RESOLUTION);

    private static final Pair<Integer, Integer> SCREEN_RESOLUTION = DEFAULT_RESOLUTION;

    private final List<Pair<Integer, Integer>> supportedResolutions = new ArrayList<>();

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
    public void setSelectedResolution(final Pair<Integer, Integer> selectedResolution) {
        if (this.supportedResolutions.isEmpty()) {
            this.loadScreenResolutions();
        }

        if (!this.supportedResolutions.contains(selectedResolution)) {
            this.selectedResolution = DEFAULT_RESOLUTION;
        } else {
            this.selectedResolution = selectedResolution;
        }

        if (this.selectedResolution.getKey().equals(SettingsImpl.DEFAULT_X_RESOLUTION) &&
            this.selectedResolution.getValue().equals(SettingsImpl.DEFAULT_Y_RESOLUTION)) {
            this.setFullScreen(true);
        } else {
            this.setFullScreen(false);
        }
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
        return Math.min((double) this.selectedResolution.getValue() / SettingsImpl.DEFAULT_RESOLUTION.getValue(), 
            (double) this.selectedResolution.getKey() / SettingsImpl.DEFAULT_RESOLUTION.getKey());
    }

    @Override
    public Set<Integer> getSupportedFPS() {
        return this.supportedFPS;
    }

    @Override
    public List<Pair<Integer, Integer>> getSupportedResolutions() {
        this.loadScreenResolutions();
        return this.supportedResolutions.stream().distinct().collect(Collectors.toList());
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
    
    private void loadScreenResolutions() {
        this.supportedResolutions.clear();
        for (int i = SettingsImpl.FIRST_SCREEN_PROPORTION; i <= SettingsImpl.LAST_SCREEN_PROPORTION; i++) {
            this.supportedResolutions.add(new ImmutablePair<>(
                (int) SettingsImpl.SCREEN_RESOLUTION.getKey() * i / SettingsImpl.LAST_SCREEN_PROPORTION,
                (int) SettingsImpl.SCREEN_RESOLUTION.getValue() * i / SettingsImpl.LAST_SCREEN_PROPORTION));
        }
    }

    @Override
    public Pair<Integer, Integer> getDefaultResolutions() {
        return SettingsImpl.DEFAULT_RESOLUTION;
    }

}
