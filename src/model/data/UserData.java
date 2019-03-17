package model.data;

import java.io.Serializable;
import java.util.Map;

import model.achievements.Achievement;
import model.achievements.AchievementType;

/**
 *
 * Represents the data of a single player's account.
 * 
 * @author simone
 */

public interface UserData extends Serializable {

    /**
     * 
     * @return a map containing achievements of a single player's account
     */
    Map<AchievementType, Achievement> getAchievements();

    /**
     * 
     * @param matchdata data of the just ended match
     */
    void addMatchData(MatchData matchdata);

    /**
     * 
     * @return the username of the player
     */
    String getName();
}
