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
        // TODO Auto-generated method stub
        return this.score;
    }

    @Override
    public void incrementKilledDucks() {
        // TODO Auto-generated method stub
        this.killedDucks++;
    }

    @Override
    public int getKilledDucks() {
        // TODO Auto-generated method stub
        return this.killedDucks;
    }

    @Override
    public void incrementFlownDucks() {
        // TODO Auto-generated method stub
        this.flownDucks++;
    }

    @Override
    public int getFlownDucks() {
        // TODO Auto-generated method stub
        return this.flownDucks;
    }

    @Override
    public void incrementScoreOf(final int score) {
        // TODO Auto-generated method stub
        this.score += score;

    }

    @Override
    public void decrementScoreOf(final int negativeScore) {
        // TODO Auto-generated method stub
        this.score -= negativeScore;
    }

    @Override
    public int getTimerFromStart() {
        // TODO Auto-generated method stub
        return this.timer;
    }

    @Override
    public void addTimeToTimer(final int timeElapsed) {
        // TODO Auto-generated method stub
        this.timer += timeElapsed;
    }

    @Override
    public void powerUpCollected(final PowerUpType type) { 
        // TODO Auto-generated method stub
        this.usedPowerUps++;
        if (!this.hasPowerUp()) {
            this.powerUp = Optional.of(type);
        }
    }

    @Override
    public boolean hasPowerUp() { 
        // TODO Auto-generated method stub
        return this.powerUp.isPresent();
    }

    @Override
    public void powerUpEnded() { 
        // TODO Auto-generated method stub
        this.powerUp = Optional.empty();
    }

    @Override
    public int getNumberOfUsedPowerUps() {
        // TODO Auto-generated method stub
        return this.usedPowerUps;
    }

    @Override
    public UnmodifiableMatchData unmodifiableCopy() {
        // TODO Auto-generated method stub
        return new UnmodifiableMatchData(new MatchDataImpl());
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
