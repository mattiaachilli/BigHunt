package model.data;

import utility.GameMode;

/**
 * 
 * @author simone
 *
 *         Represents the data of an unmodifiable match
 */
public class UnmodifiableMatchData implements MatchData {

    private final MatchData matchdata;

    public UnmodifiableMatchData(final MatchData matchdata) {
        this.matchdata = matchdata;
    }

    @Override
    public int getGlobalScore() {
        return this.matchdata.getGlobalScore();
    }

    @Override
    public int getNumberOfKilledDucks() {
        // TODO Auto-generated method stub
        return this.matchdata.getNumberOfKilledDucks();
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
    public int getCurrentRound() {
        return this.matchdata.getCurrentRound();
    }

    @Override
    public boolean isMatchGoing() {
        return this.matchdata.isMatchGoing();
    }

    /*
     * @Override public boolean hasPowerUp() { // TODO Auto-generated method stub
     * return this.matchdata.hasPowerUp(); }
     */
    @Override
    public void incrementNumberOdKilledDucks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void incrementScoreOf(int score) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decrementScoreOf(int negativeScore) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addTimeToTimer(int timeElapsed) {
        throw new UnsupportedOperationException();
    }
    /*
     * @Override public void powerUpEnded() { // TODO Auto-generated method throw
     * new UnsupportedOperationException();
     * 
     * }
     * 
     * @Override public void powerUpCollected(PowerUpType type) { // TODO
     * Auto-generated method stub throw new UnsupportedOperationException(); }
     */


    @Override
    public void pauseMatch() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unpauseMatch() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void incrementRound() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UnmodifiableMatchData unmodifiableCopy() {
        return this;
    }

    @Override
    public GameMode getMode() {
        // TODO Auto-generated method stub
        return this.matchdata.getMode();
    }
}