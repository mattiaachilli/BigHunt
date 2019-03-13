package settings.observers;



import javafx.scene.control.CheckBox;


import settings.SettingsImpl;
import settings.utilities.SettingsCheckBox;

/**
 * 
 * this class implements audio observer changes.
 *
 */
public class BackGroundAudioObserver implements Observer {

    private final CheckBox bgAudioCheckbox;
    
    /**
     * 
     * @param bgAudioCheckbox
     *          the graphics checkBox where users can change the audio settings
     */
    public BackGroundAudioObserver(final CheckBox bgAudioCheckbox) {
        this.bgAudioCheckbox = bgAudioCheckbox;
        this.bgAudioCheckbox.setSelected(SettingsImpl.getSettings().isBackgroundAudioActivated());
        this.bgAudioCheckbox.selectedProperty().addListener(e -> this.setText());
        this.setText();
    }
    
    @Override
    public void update() {
       SettingsImpl.getSettings().setBackgroundAudio(this.bgAudioCheckbox.isSelected());
    }
    
    private void setText() {
        this.bgAudioCheckbox.setText(
            this.bgAudioCheckbox.isSelected() ? SettingsCheckBox.CHECKED.getText() : SettingsCheckBox.UNCHECKED.getText());
    }

}
