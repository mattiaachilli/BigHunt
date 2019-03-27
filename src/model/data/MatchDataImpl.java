package model.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

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
    private int usedPowerUps;
    private Optional<PowerUpType> powerUp;

    /**
     * 
     * Constructor do be called when the player starts a new match.
     */
    public MatchDataImpl() {
        this.score = 0;
        this.killedDucks = 0;
        this.flownDucks = 0;
        this.timer = 0;
        this.usedPowerUps = 0;
        this.powerUp = Optional.empty();
    }

    @Override
    public int getGlobalScore() {
        return this.score;
    }

    @Override
    public void incrementKilledDucks() {
        this.killedDucks++;
    }

    @Override
    public int getKilledDucks() {
        return this.killedDucks;
    }

    @Override
    public void incrementFlownDucks() {
        this.flownDucks++;
    }

    @Override
    public int getFlownDucks() {
        return this.flownDucks;
    }

    @Override
    public void incrementScoreOf(final int score) {
        this.score += score;

    }

    @Override
    public void decrementScoreOf(final int negativeScore) {
        this.score -= negativeScore;
    }

    @Override
    public int getTimerFromStart() {
        return this.timer;
    }

    @Override
    public void addTimeToTimer(final int timeElapsed) {
        this.timer += timeElapsed;
    }

    @Override
    public void powerUpCollected(final PowerUpType type) { 
        this.usedPowerUps++;
        if (!this.hasPowerUp()) {
            this.powerUp = Optional.of(type);
        }
    }

    @Override
    public boolean hasPowerUp() { 
        return this.powerUp.isPresent();
    }

    @Override
    public void powerUpEnded() { 
        this.powerUp = Optional.empty();
    }

    @Override
    public int getNumberOfUsedPowerUps() {
        return this.usedPowerUps;
    }

    @Override
    public UnmodifiableMatchData unmodifiableCopy() {
        return new UnmodifiableMatchData(new MatchDataImpl());
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
