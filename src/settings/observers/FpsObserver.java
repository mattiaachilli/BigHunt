package settings.observers;

import javafx.scene.control.ComboBox;
import settings.SettingsFPS;
import settings.SettingsImpl;

/**
 *
 * Implements FPS settings changes.
 *
 */
public class FpsObserver implements Observer {

    private final ComboBox<String> fpsComboBox;

    /**
     *
     * @param fpsComboBox
     *          comboBox list of game rate
     */
    public FpsObserver(final ComboBox<String> fpsComboBox) {
        this.fpsComboBox = fpsComboBox;
        this.addItem();
        this.setDefaultValue();
    }

    @Override
    public final void update() {
        SettingsImpl.getSettings().setSelectedFPS(this.fpsComboBox.getValue());
    }

    private void addItem() {
        for (int i = 0; i < SettingsFPS.values().length; i++) {
            this.fpsComboBox.getItems().add(SettingsFPS.values()[i].getFPS());
        }
    }

    private void setDefaultValue() {
        this.fpsComboBox.setValue(SettingsImpl.getSettings().getSelectedFPS().getFPS());
    }

}
