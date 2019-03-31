package view.scenecontroller;

import model.data.MatchData;

/**
 * 
 *represents the scene to be load after end game.
 *
 */
public interface GameOverSceneController {

    /**
     * 
     * @param matchData
     *          game data received.
     */
    void setMatchData(MatchData matchData);
}
