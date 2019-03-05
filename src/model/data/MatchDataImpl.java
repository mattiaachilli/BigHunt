package model.data;

import java.util.Optional;

public class MatchDataImpl implements MatchData {
    
    private int score;
    private int killedDucks;
    private int timer;
    private int usedPowerUps;
    private Optional<PowerUpType> powerUp;
    
    /**
     * 
     * Constructor do be called when the player starts a new match
     */
    public MatchDataImpl(){
        this.score = 0;
        this.killedDucks = 0;
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
    public void incrementNumberOdKilledDucks() {
        // TODO Auto-generated method stub
        this.killedDucks++;
    }

    @Override
    public int getNumberOfKilledDucks() {
        // TODO Auto-generated method stub
        return this.killedDucks;
    }

    @Override
    public void incrementScoreOf(int score) {
        // TODO Auto-generated method stub
        this.score += score;
    }

    @Override
    public void decrementScoreOf(int negativeScore) {
        // TODO Auto-generated method stub
        this.score -= negativeScore;
    }

    @Override
    public int getTimerFromStart() {
        // TODO Auto-generated method stub
        return this.timer;
    }

    @Override
    public void addTimeToTimer(int timeElapsed) {
        // TODO Auto-generated method stub
        this.timer += timeElapsed;
    }

    @Override
    public void powerUpCollected(PowerUpType type) {
        // TODO Auto-generated method stub
        this.usedPowerUps++;
        if(!this.hasPowerUp()) {
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
    public UnmodifiableMatchData unmodifiableCopy(MatchData matchdata) {
        // TODO Auto-generated method stub
        return new UnmodifiableMatchData(matchdata);
    }

}
