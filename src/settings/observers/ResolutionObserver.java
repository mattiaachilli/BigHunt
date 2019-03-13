package settings.observers;

import java.util.stream.Collectors;

import javafx.scene.control.ComboBox;
import settings.SettingsImpl;

public class ResolutionObserver implements Observer {

    /**
     * Resolution Observer Class
     */
    private final ComboBox<String> resolutionComboBox;
    
    /**
     * 
     * @param resolutionComboBox
     *          the comboBox where we can change the resoution
     */
    public ResolutionObserver(final ComboBox<String> resolutionComboBox) {
        this.resolutionComboBox = resolutionComboBox;
        this.inizialize();
    }
    
    @Override
    public void update() {
        SettingsImpl.getSettings().setSelectedResolution(SettingsImpl.getSettings().getSupportedResolutions()
        .get(this.resolutionComboBox.getSelectionModel().getSelectedIndex()));
    }
    
    private void inizialize() {
        this.resolutionComboBox.getItems().addAll(SettingsImpl.getSettings().getSupportedResolutions().stream()
        .map(resolution -> resolution.getKey() + "*" + resolution.getValue()).collect(Collectors.toList()));
        
        this.resolutionComboBox.setValue(SettingsImpl.getSettings().getSelectedResolution().getKey() + "*" + 
            SettingsImpl.getSettings().getSelectedResolution().getValue());
        
        this.resolutionComboBox.getSelectionModel().select(this.resolutionComboBox.getSelectionModel()
            .getSelectedItem());
        
        if(this.resolutionComboBox.getSelectionModel().getSelectedIndex() == -1) {
            this.resolutionComboBox.getSelectionModel()
                .select(SettingsImpl.getSettings().getSupportedResolutions().size() / 2);
            this.update();
        }
    }

}
