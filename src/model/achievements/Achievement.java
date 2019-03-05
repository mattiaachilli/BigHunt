package model.data;

import java.util.Optional;
import java.util.Set;

public interface Achievement {
    
    /**
     * 
     * @return the type of the achievement
     */
    public AchievementType getAchievementType();
    
    /**
     * 
     * @return all the targets for this type of achievement
     */
    public Set<Integer> getAllTargets();
    
    /**
     * 
     * @return the next target to achieve
     */
    public Optional<Integer> getNextTarget();
    
    /**
     * 
     * @return the current value of this type of achievement
     */
    public int getCurrentValueOfAchievement();
}
