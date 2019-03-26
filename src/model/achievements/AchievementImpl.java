package model.achievements;

import java.util.Optional;
import java.util.Set;

/**
 * The class representing achievements.
 * @author simone
 */
public class AchievementImpl implements Achievement {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final AchievementType type;
    private int value;

    /**
     * Constructor of a new achievement.
     * @param type
     *          the achievement type
     * @param value
     *          its value
     */
    public AchievementImpl(final AchievementType type, final int value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public AchievementType getAchievementType() {
        return this.type;
    }

    @Override
    public Set<Integer> getAllTargets() {
        return this.type.getTargets();
    }

    @Override
    public Optional<Integer> getNextTarget() {
        return this.getAllTargets().stream().filter(t -> t > this.value).findFirst();
    }

    @Override
    public int getCurrentValueOfAchievement() {
        return this.value;
    }
}