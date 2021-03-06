package controller.files;

import java.util.Optional;

import model.data.Podium;

/**
 * An interface of the file manager for podium (high scores) files.
 *
 */
public interface PodiumManager {

    /**
     * 
     * @return the story high scores
     */
    Optional<Podium> loadStoryPodium();

    /**
     * 
     * @return the survival high scores
     */
    Optional<Podium> loadSurvivalPodium();

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
