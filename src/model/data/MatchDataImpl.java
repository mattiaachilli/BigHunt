package model.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import model.entities.powerup.PowerUpType;

/**
 * The implementation of the data of a match.
 * @author simone
 *
 */
public class MatchDataImpl implements MatchData {

    private int score;
    private int killedDucks;
    private int flownDucks;
    private int timer;
    private Map<PowerUpType, Integer> powerUps;

    /**
     * 
     * Constructor do be called when the player starts a new match.
     */
    public MatchDataImpl() {
        this.score = 0;
        this.killedDucks = 0;
        this.flownDucks = 0;
        this.timer = 0;
        this.powerUps = this.initPowerUps();
        //this.powerUp = Optional.empty();
    }

    private Map<PowerUpType, Integer> initPowerUps() {
        Map<PowerUpType, Integer> powerUps = new HashMap<PowerUpType, Integer>();
        for (PowerUpType t : PowerUpType.values()) {
            powerUps.put(t, 0);
        }
        return powerUps;
    }

    @Override
    public final int getGlobalScore() {
        return this.score;
    }

    @Override
    public final void incrementKilledDucks() {
        this.killedDucks++;
    }

    @Override
    public final int getKilledDucks() {
        return this.killedDucks;
    }

    @Override
    public final void incrementFlownDucks() {
        this.flownDucks++;
    }

    @Override
    public final int getFlownDucks() {
        return this.flownDucks;
    }

    @Override
    public final void incrementScoreOf(final int score) {
        this.score += score;

    }

    @Override
    public final void decrementScoreOf(final int negativeScore) {
        this.score -= negativeScore;
    }

    @Override
    public final int getTimerFromStart() {
        return this.timer;
    }

    @Override
    public final void addTimeToTimer(final int timeElapsed) {
        this.timer += timeElapsed;
    }

    @Override
    public final void powerUpCollected(final PowerUpType type) { 
        final Integer oldValue = this.powerUps.get(type);
        this.powerUps.put(type, oldValue + 1);
    }

    @Override
    public final int getNumberOfUsedPowerUps() {
        return this.powerUps.values().stream().reduce(0, Integer::sum);
    }

    @Override
    public final UnmodifiableMatchData unmodifiableCopy() {
        return new UnmodifiableMatchData(new MatchDataImpl());
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

}
