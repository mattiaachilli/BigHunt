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

public final class SettingsImpl implements Settings {

    private static final int DEFAULT_X_RESOLUTION = 890;
    private static final int DEFAULT_Y_RESOLUTION = 600;
    private static final int FIRST_SCREEN_PROPORTION = 3;
    private static final int LAST_SCREEN_PROPORTION = 8;
    private static final Pair<Integer, Integer> DEFAULT_RESOLUTION = new ImmutablePair<>(DEFAULT_X_RESOLUTION,
        DEFAULT_Y_RESOLUTION);
    private static final Pair<Integer, Integer> SCREEN_RESOLUTION = new ImmutablePair<>(
        (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
        (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    private final List<Pair<Integer, Integer>> supportedResolutions = new ArrayList<>();
    private Pair<Integer, Integer> selectedResolution = new ImmutablePair<>(
        SettingsImpl.SCREEN_RESOLUTION.getKey() * 3 / 4, SettingsImpl.SCREEN_RESOLUTION.getValue() * 3 / 4);
    private static final int DEFAULT_FPS = 60;
    private final Set<Integer> supportedFPS = new TreeSet<>(Arrays.asList(10, 20, 30, 60, GraphicsEnvironment
        .getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate()));
    private int selectedFPS = DEFAULT_FPS;
    
    private boolean fullScreen;
    private boolean backgroundAudio = true;
    
    private static SettingsImpl singleton;
    
    private SettingsImpl() {}
    
    public static SettingsImpl getSettings() {
        if(Objects.isNull(singleton)) {
            singleton = new SettingsImpl();
        }
        return singleton;
    }
    
    @Override
    public void setSelectedResolution(Pair<Integer, Integer> selectedResolution) {
        if(this.supportedResolutions.isEmpty()) {
            this.setScreenResolutions();
        }
        
        if(!this.supportedResolutions.contains(selectedResolution)) {
            this.selectedResolution = this.supportedResolutions.get(this.supportedResolutions.size() / 2);
        } else {
            this.selectedResolution = selectedResolution;
        }
        
        if(this.selectedResolution.getKey().equals(SettingsImpl.SCREEN_RESOLUTION.getKey()) &&
            this.selectedResolution.getValue().equals(SettingsImpl.SCREEN_RESOLUTION.getValue())) {
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
    public void setSelectedFPS(int selectedFPS) {
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
        this.setScreenResolutions();
        return this.supportedResolutions.stream().distinct().collect(Collectors.toList());
    }
    
    @Override
    public void setFullScreen(boolean fullScreen) {
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
    public void setBackgroundAudio(boolean backgroundAudio) {
        this.backgroundAudio = backgroundAudio;
    }
    
    private void setScreenResolutions() {
        this.supportedResolutions.clear();
        for(int i = SettingsImpl.FIRST_SCREEN_PROPORTION; i <= SettingsImpl.LAST_SCREEN_PROPORTION; i++) {
            this.supportedResolutions.add(new ImmutablePair<>((int) SettingsImpl.SCREEN_RESOLUTION.getKey() * i / 8,
                (int) SettingsImpl.SCREEN_RESOLUTION.getValue() * i / SettingsImpl.LAST_SCREEN_PROPORTION));
        }
    }

}
