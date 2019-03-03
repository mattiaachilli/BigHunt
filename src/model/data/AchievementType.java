package model.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author simone
 *
 */

public enum AchievementType {
    
    /**
     * The number of matches that the player has played
     */
    PLAYED_MATCHES(1, 2, 5, 10, 20),
    
    /**
     * The number of killed ducks by the player
     */
    KILLED_DUCKS(5, 10, 20, 50, 100, 250),
    
    /**
     * The number of power-ups used by the player
     */
    USED_POWERUPS(2, 5, 10, 20, 50);
    
    /**
     * A set representing the targets (steps) of a single achievement
     */
    private final Set<Integer> targets;
    
    /**
     * Initialize the steps of the achievement
     * 
     * @param steps, values of the achievement
     */
    AchievementType(final Integer... steps) {
        this.targets = new HashSet<>(Arrays.asList(steps));
    }
    
    /**
     * 
     * @return a set of the targets (steps) of this type of achievement
     */
    public Set<Integer> getTargets() {
        return this.targets;
    }
        
    
}
