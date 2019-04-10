package model.achievements;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * An enumeration containing the different achievement types and their targets.
 * 
 * @author simone
 */
public enum AchievementType {

    /**
     * The number of matches that the player has played.
     */
    MATCHES_PLAYED(1, 5, 10, 20, 30),

    /**
     * The number of killed ducks by the player.
     */
    KILLED_DUCKS(5, 10, 20, 50, 100),

    /**
     * The number of power-ups used by the player.
     */
    POWERUPS_USED(2, 5, 10, 20, 30),

    /**
     * The sum of all the scores of the games played by the player.
     */
    SUM_OF_SCORES(1000, 5000, 10000, 20000, 50000);

    /**
     * A set representing the targets (steps) of a single achievement.
     */
    private final Set<Integer> targets;

    /**
     * Initialize the steps of the achievement.
     * 
     * @param steps
     *          values of the achievement
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
