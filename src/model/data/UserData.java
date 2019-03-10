package model.data;

import java.io.Serializable;
import java.util.Map;

import model.achievements.Achievement;
import model.achievements.AchievementType;

/**
 * 
 * 
 * Represents the data of a single player's account
 */

public interface UserData extends Serializable {
    
    /** 
     * 
     * @return a map containing achievements of a single player's account
     */
    public Map<AchievementType, Achievement> getAchievements();
    
    /**
     * 
     * @param matchdata, data of the just ended match 
     */
    public void addMatchData(final MatchData matchdata);
    
    /**
     * 
     * @return the username of the player
     */
    public String getName();
}
