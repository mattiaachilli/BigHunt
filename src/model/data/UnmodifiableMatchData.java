package model.data;

import model.entities.powerup.PowerUpType;

/**
 * 
 * Represents the data of an unmodifiable match.
 * 
 * @author simone
 */
public class UnmodifiableMatchData implements MatchData {

    private final MatchData matchdata;

    /**
     * Constructor of an unmodifiable match data.
     * 
     * @param matchdata the matchdata to copy
     */
    public UnmodifiableMatchData(final MatchData matchdata) {
        this.matchdata = matchdata;
    }

    @Override
    public int getGlobalScore() {
        return this.matchdata.getGlobalScore();
    }

    @Override
    public int getKilledDucks() {
        // TODO Auto-generated method stub
        return this.matchdata.getKilledDucks();
    }

    @Override
    public int getTimerFromStart() {
        // TODO Auto-generated method stub
        return this.matchdata.getTimerFromStart();
    }

    @Override
    public int getNumberOfUsedPowerUps() {
        // TODO Auto-generated method stub
        return this.matchdata.getNumberOfUsedPowerUps();
    }

    @Override
    public void incrementKilledDucks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void incrementScoreOf(final int score) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decrementScoreOf(final int negativeScore) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addTimeToTimer(final int timeElapsed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void incrementFlownDucks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public int getFlownDucks() {
        // TODO Auto-generated method stub
        return this.matchdata.getFlownDucks();
    }

    @Override
    public void powerUpCollected(final PowerUpType type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasPowerUp() {
        // TODO Auto-generated method stub
        return this.matchdata.hasPowerUp();
    }

    @Override
    public void powerUpEnded() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public UnmodifiableMatchData unmodifiableCopy() {
        return this;
    }

}
