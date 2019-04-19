package view.scenecontroller;

import model.data.MatchData;

/**
 * 
 *represents the scene to be loaded after the end of a game.
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
