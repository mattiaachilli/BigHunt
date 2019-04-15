package settings.observers;



import javafx.scene.control.CheckBox;
import settings.Settings;
import settings.SettingsAudio;
import settings.SettingsImpl;

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
        this.setDefaultSelection();
        this.setDefaultText();
        this.addActionListener();
    }

    @Override
    public final void update() {
       SettingsImpl.getSettings().setBackgroundAudio(this.bgAudioCheckbox.isSelected());
    }

    private void setDefaultSelection() {
        this.bgAudioCheckbox.setSelected(SettingsImpl.getSettings().isBackgroundAudioOn());
    }

    private void setDefaultText() {
        this.bgAudioCheckbox.setText(SettingsImpl.getSettings().isBackgroundAudioOn() ? SettingsAudio.AUDIO_ON.getText() : SettingsAudio.AUDIO_OFF.getText());
    }

    private void addActionListener() {
        this.bgAudioCheckbox.selectedProperty().addListener(e -> {
            this.bgAudioCheckbox.setText(this.bgAudioCheckbox.isSelected() ? SettingsAudio.AUDIO_ON.getText() : SettingsAudio.AUDIO_OFF.getText());
        });
        this.bgAudioCheckbox.selectedProperty().addListener(e -> {
            if (this.bgAudioCheckbox.isSelected()) {
                SettingsImpl.getSettings().setBackgroundAudio(true);
            } else {
                SettingsImpl.getSettings().setBackgroundAudio(false);
            }
        });
    }

}
