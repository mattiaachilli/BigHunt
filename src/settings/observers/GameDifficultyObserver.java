package settings.observers;

import javafx.scene.control.ComboBox;
import settings.GlobalDifficulty;
import settings.SettingsImpl;
/**
 *
 * this class implements the game difficulty changes observer.
 */
public class GameDifficultyObserver implements Observer {

    private final ComboBox<String> difficultyComboBox;

    /**
     *
     * @param difficultyComboBox
     *                  the ComboBox where user can change difficulty
     */
    public GameDifficultyObserver(final ComboBox<String> difficultyComboBox) {
        this.difficultyComboBox = difficultyComboBox;
        this.addItem();
        this.setDefaultValue();
    }

    @Override
    public final void update() {
       SettingsImpl.getSettings().setDifficulty(this.difficultyComboBox.getValue());
    }

    private void addItem() {
        for (int i = 0; i < GlobalDifficulty.values().length; i++) {
            this.difficultyComboBox.getItems().add(GlobalDifficulty.values()[i].getGlobalDifficulty());
        }
    }

    private void setDefaultValue() {
        this.difficultyComboBox.setValue(SettingsImpl.getSettings().getSelectedDifficulty().toString());
    }

}
