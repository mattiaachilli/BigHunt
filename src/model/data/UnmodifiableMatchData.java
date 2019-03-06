package model.data;

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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return this.matchdata.getCurrentRound();
    }

    @Override
    public boolean isMatchGoing() {
        // TODO Auto-generated method stub
        return this.matchdata.isMatchGoing();
    }

    /*
     * @Override public boolean hasPowerUp() { // TODO Auto-generated method stub
     * return this.matchdata.hasPowerUp(); }
     */
    @Override
    public void incrementNumberOdKilledDucks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public void incrementScoreOf(int score) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public void decrementScoreOf(int negativeScore) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public void addTimeToTimer(int timeElapsed) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public void unpauseMatch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public void incrementRound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public UnmodifiableMatchData unmodifiableCopy(final MatchData matchdata) {
        // TODO Auto-generated method stub
        return this;
    }
}
