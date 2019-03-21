package model.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

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

    private Map<AchievementType, Achievement> achievements;

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

        this.achievements = new EnumMap<>(AchievementType.class);
    }

    @Override
    public Map<AchievementType, Achievement> getAchievements() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableMap(this.achievements);
    }

    @Override
    public void updateAchievements() {
        // TODO Auto-generated method stub
        for (AchievementType type : AchievementType.values()) {
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

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return this.name;
    }

    @Override
    public void addMatchData(final MatchData matchdata) {
        // TODO Auto-generated method stub
        this.matchesPlayed++;
        this.killedDucks += matchdata.getKilledDucks();
        this.globalScore += matchdata.getGlobalScore();
        this.powerUpsUsed += matchdata.getNumberOfUsedPowerUps();
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}