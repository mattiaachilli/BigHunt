package model.data;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class PlayerDataImpl implements PlayerData {

    private int matchesPlayed;
    private int killedDucks;
    private int powerUpsUsed;
    private int globalScore;

    /**
     * Constructor to be used when a new account is created
     */
    public PlayerDataImpl() {
        this.matchesPlayed = 0;
        this.killedDucks = 0;
        this.powerUpsUsed = 0;
        this.globalScore = 0;
    }

    @Override
    public Map<AchievementType, Achievement> getAchievements() {
        // TODO Auto-generated method stub
        Map<AchievementType, Achievement> achievements = new EnumMap<>(AchievementType.class);
        for (AchievementType type : AchievementType.values()) {
            switch (type) {

            case KILLED_DUCKS:
                achievements.put(type, new AchievementImpl(type, this.killedDucks));
                break;

            case MATCHES_PLAYED:
                achievements.put(type, new AchievementImpl(type, this.matchesPlayed));
                break;

            case POWERUPS_USED:
                achievements.put(type, new AchievementImpl(type, this.powerUpsUsed));
                break;

            case SUM_OF_SCORES:
                achievements.put(type, new AchievementImpl(type, this.globalScore));
                break;

            default:
                break;
            }
        }
        return Collections.unmodifiableMap(achievements);
    }

    @Override
    public void addMatchData(final MatchData matchdata) {
        // TODO Auto-generated method stub
        this.matchesPlayed++;
        this.killedDucks += matchdata.getNumberOfKilledDucks();
        this.globalScore += matchdata.getGlobalScore();
        this.powerUpsUsed += matchdata.getNumberOfUsedPowerUps();
    }

}
