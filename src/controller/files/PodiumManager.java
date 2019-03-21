package controller.files;

import java.util.Optional;

import model.data.Podium;

/**
 * An interface of the file manager for podium (high scores) files.
 * @author simone
 *
 */
public interface PodiumManager {

    /**
     * 
     * @return the story high scores
     */
    Optional<Podium> loadStoryHighScores();

    /**
     * 
     * @return the survival high scores
     */
    Optional<Podium> loadSurvivalHighScores();

    /**
     * @param podium
     *          the podium to be saved
     * @return true if the story scores are correctly saved
     */
    boolean saveStoryHighScores(Podium podium);

    /**
     * @param podium
     *          the podium to be saved
     * @return true if the survival scores are correctly saved
     */
    boolean saveSurvivalHighScores(Podium podium);
}