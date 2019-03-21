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
     * Updates the user's achievements after an ended match.
     * It replaces the old values of achievements with the new ones.
     */
    void updateAchievements();

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