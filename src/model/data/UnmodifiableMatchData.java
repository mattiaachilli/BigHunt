package model.data;

import model.entities.powerup.PowerUpType;

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

    /*
     * @Override public boolean hasPowerUp() { // TODO Auto-generated method stub
     * return this.matchdata.hasPowerUp(); }
     */
    @Override
    public void incrementKilledDucks() {
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
    public void powerUpCollected(PowerUpType type) {
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
