package model.achievements;

import java.util.Optional;
import java.util.Set;

/**
 * An interface of an achievement.
 * @author simone
 */
public interface Achievement {

    /**
     * 
     * @return the type of the achievement
     */
    AchievementType getAchievementType();

    /**
     * 
     * @return all the targets for this type of achievement
     */
    Set<Integer> getAllTargets();

    /**
     * 
     * @return the next target to achieve
     */
    Optional<Integer> getNextTarget();

    /**
     * 
     * @return the current value of this type of achievement
     */
    int getCurrentValueOfAchievement();
}
