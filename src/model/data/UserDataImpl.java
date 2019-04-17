package model.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import model.achievements.Achievement;
import model.achievements.AchievementImpl;
import model.achievements.AchievementType;

/**
 * The serializable data of a single user.
 * 
 * @author simone
 *
 */
public class UserDataImpl implements UserData {

    private static final long serialVersionUID = 1L;

    private final String name;

    private int matchesPlayed;
    private int killedDucks;
    private int powerUpsUsed;
    private int globalScore;

    private final Map<AchievementType, Achievement> achievements;

    /**
     * Constructor to be used when a new account is created.
     * 
     * @param name the user's username
     */
    public UserDataImpl(final String name) {
        this.name = name;

        this.matchesPlayed = 0;
        this.killedDucks = 0;
        this.powerUpsUsed = 0;
        this.globalScore = 0;

        this.achievements = Arrays.stream(AchievementType.values())
            .collect(Collectors.toMap(t -> t, t -> new AchievementImpl(t, 0)));
    }

    @Override
    public final Map<AchievementType, Achievement> getAchievements() {
        return Collections.unmodifiableMap(this.achievements);
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void addMatchData(final MatchData matchdata) {
        this.matchesPlayed++;
        this.killedDucks += matchdata.getKilledDucks();
        this.globalScore += matchdata.getGlobalScore();
        this.powerUpsUsed += matchdata.getNumberOfUsedPowerUps();
        this.updateAchievements();
    }

    private void updateAchievements() {
        for (final AchievementType type : AchievementType.values()) {
            switch (type) {

            case KILLED_DUCKS:
                this.achievements.put(type, new AchievementImpl(type, this.killedDucks));
                break;

            case MATCHES_PLAYED:
                this.achievements.put(type, new AchievementImpl(type, this.matchesPlayed));
                break;

            case POWERUPS_USED:
                this.achievements.put(type, new AchievementImpl(type, this.powerUpsUsed));
                break;

            case SUM_OF_SCORES:
                this.achievements.put(type, new AchievementImpl(type, this.globalScore));
                break;

            default:
                break;
            }
        }
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
