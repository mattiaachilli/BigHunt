package model.achievements;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private final int value;

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
    public final AchievementType getAchievementType() {
        return this.type;
    }

    @Override
    public final Set<Integer> getAllTargets() {
        return this.type.getTargets();
    }

    @Override
    public final Optional<Integer> getNextTarget() {
        return this.getAllTargets().stream().filter(t -> t > this.value).findFirst();
    }

    @Override
    public final int getCurrentValueOfAchievement() {
        return this.value;
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
