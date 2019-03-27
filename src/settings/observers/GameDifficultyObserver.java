package settings.observers;

import javafx.scene.control.ComboBox;
import settings.SettingsImpl;

public class GameDifficultyObserver implements Observer{

    private final ComboBox<String> difficultyComboBox;
    
    public GameDifficultyObserver(final ComboBox<String> difficultyComboBox) {
        this.difficultyComboBox = difficultyComboBox;
        this.difficultyComboBox.getItems().addAll(SettingsImpl.getSettings().getGameDifficulties());
        this.difficultyComboBox.setValue(SettingsImpl.getSettings().getSelectedDifficulty());
    }
    
    @Override
    public void update() {
       SettingsImpl.getSettings().setDifficulty(this.difficultyComboBox.getValue());
    }

}
