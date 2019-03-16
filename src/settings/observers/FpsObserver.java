package settings.observers;

import javafx.scene.control.ComboBox;
import settings.SettingsImpl;

/**
 * 
 * Implements FPS settings changes.
 *
 */
public class FpsObserver implements Observer {

    private final ComboBox<Integer> fpsComboBox;

    /**
     * 
     * @param fpsComboBox
     *          comboBox list of game rate
     */
    public FpsObserver(final ComboBox<Integer> fpsComboBox) {
        this.fpsComboBox = fpsComboBox;
        this.fpsComboBox.getItems().addAll(SettingsImpl.getSettings().getSupportedFPS());
        this.fpsComboBox.setValue(SettingsImpl.getSettings().getSelectedFPS());
    }

    @Override
    public final void update() {
        SettingsImpl.getSettings().setSelectedFPS(this.fpsComboBox.getValue());

    }

}
