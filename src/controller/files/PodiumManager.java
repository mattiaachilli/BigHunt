package controller.files;

import java.util.Optional;

import model.data.Podium;

public interface PodiumManager {

    /**
     * If it is present
     * @return the story high scores
     */
    public Optional<Podium> loadStoryHighScores();
    
    /**
     * If it is present
     * @return the survival high scores
     */
    public Optional<Podium> loadSurvivalHighScores();
    
    /**
     * 
     * @return true if the story scores are correctly saved
     */
    public boolean saveStoryHighScores(final Podium podium);
    
    /**
     * 
     * @return true if the survival scores are correctly saved
     */
    public boolean saveSurvivalHighScores(final Podium podium);

}
