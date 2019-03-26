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
    public final int getGlobalScore() {
        return this.matchdata.getGlobalScore();
    }

    @Override
    public final int getKilledDucks() {
        // TODO Auto-generated method stub
        return this.matchdata.getKilledDucks();
    }

    @Override
    public final int getTimerFromStart() {
        // TODO Auto-generated method stub
        return this.matchdata.getTimerFromStart();
    }

    @Override
    public final int getNumberOfUsedPowerUps() {
        // TODO Auto-generated method stub
        return this.matchdata.getNumberOfUsedPowerUps();
    }

    @Override
    public final void incrementKilledDucks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void incrementScoreOf(final int score) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void decrementScoreOf(final int negativeScore) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void addTimeToTimer(final int timeElapsed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void incrementFlownDucks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public final int getFlownDucks() {
        // TODO Auto-generated method stub
        return this.matchdata.getFlownDucks();
    }

    @Override
    public final void powerUpCollected(final PowerUpType type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean hasPowerUp() {
        // TODO Auto-generated method stub
        return this.matchdata.hasPowerUp();
    }

    @Override
    public final void powerUpEnded() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public final UnmodifiableMatchData unmodifiableCopy() {
        return this;
    }
}