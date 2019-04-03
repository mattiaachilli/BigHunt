package settings.observers;

import javafx.scene.control.ComboBox;
import settings.GlobalDifficulty;
import settings.SettingsImpl;

public class GameDifficultyObserver implements Observer{

    private final ComboBox<String> difficultyComboBox;
    
    public GameDifficultyObserver(final ComboBox<String> difficultyComboBox) {
        this.difficultyComboBox = difficultyComboBox;
        for(int i = 0; i < GlobalDifficulty.values().length ; i++) {
            this.difficultyComboBox.getItems().add(GlobalDifficulty.values()[i].getGlobalDifficulty());
        }
        //this.difficultyComboBox.getItems().addAll(SettingsImpl.getSettings().getGameDifficulties().toString());
        this.difficultyComboBox.setValue(SettingsImpl.getSettings().getSelectedDifficulty().toString());
    }
    
    @Override
    public void update() {
       SettingsImpl.getSettings().setDifficulty(this.difficultyComboBox.getValue());
    }

}
