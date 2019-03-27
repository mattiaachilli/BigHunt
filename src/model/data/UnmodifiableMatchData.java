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
        return this.matchdata.getKilledDucks();
    }

    @Override
    public final int getTimerFromStart() {
        return this.matchdata.getTimerFromStart();
    }

    @Override
    public final int getNumberOfUsedPowerUps() {
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
        throw new UnsupportedOperationException();
    }

    @Override
    public final int getFlownDucks() {
        return this.matchdata.getFlownDucks();
    }

    @Override
    public final void powerUpCollected(final PowerUpType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean hasPowerUp() {
        return this.matchdata.hasPowerUp();
    }

    @Override
    public final void powerUpEnded() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final UnmodifiableMatchData unmodifiableCopy() {
        return this;
    }
}
