package model.data;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @author simone
 * 
 * Represents the data of a single player's account
 */

public interface PlayerData extends Serializable {
    
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
