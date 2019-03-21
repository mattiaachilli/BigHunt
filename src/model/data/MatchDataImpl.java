package model.data;

//import java.util.Optional;

import model.utilities.RoundUtility;
import utility.GameMode;

public class MatchDataImpl implements MatchData {

    private final RoundUtility MAX_OF_ROUNDS;
    private final GameMode GAME_MODE;

    private boolean isMatchPaused;
    private int currentRound;
    private int score;
    private int killedDucks;
    private int timer;
    private int usedPowerUps;
    // private Optional<PowerUpType> powerUp;

    /**
     * 
     * Constructor do be called when the player starts a new match
     */
    public MatchDataImpl(final GameMode gameMode) {
        this.isMatchPaused = true;
        this.currentRound = 1;
        this.score = 0;
        this.killedDucks = 0;
        this.timer = 0;
        this.usedPowerUps = 0;
        // this.powerUp = Optional.empty();
        this.GAME_MODE = gameMode;

        switch (gameMode) {
        case STORY_MODE:
            this.MAX_OF_ROUNDS = RoundUtility.FIVE_ROUNDS;
            break;
        case SURVIVAL_MODE:
            this.MAX_OF_ROUNDS = RoundUtility.UNIQUE_ROUND;
            break;
        default:
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getGlobalScore() {
        // TODO Auto-generated method stub
        return this.score;
    }

    @Override
    public void incrementNumberOdKilledDucks() {
        // TODO Auto-generated method stub
        if (this.isMatchGoing()) {
            this.killedDucks++;
        }
    }

    @Override
    public int getNumberOfKilledDucks() {
        // TODO Auto-generated method stub
        return this.killedDucks;
    }

    @Override
    public void incrementScoreOf(int score) {
        // TODO Auto-generated method stub
        if (this.isMatchGoing()) {
            this.score += score;
        }
    }

    @Override
    public void decrementScoreOf(int negativeScore) {
        // TODO Auto-generated method stub
        if (this.isMatchGoing()) {
            this.score -= negativeScore;
        }
    }

    @Override
    public int getTimerFromStart() {
        // TODO Auto-generated method stub
        return this.timer;
    }

    @Override
    public void addTimeToTimer(int timeElapsed) {
        // TODO Auto-generated method stub
        if (this.isMatchGoing()) {
            this.timer += timeElapsed;
        }
    }

    /*
     * @Override public void powerUpCollected(PowerUpType type) { // TODO
     * Auto-generated method stub this.usedPowerUps++; if (!this.hasPowerUp()) {
     * this.powerUp = Optional.of(type); } }
     * 
     * @Override public boolean hasPowerUp() { // TODO Auto-generated method stub
     * return this.powerUp.isPresent(); }
     * 
     * @Override public void powerUpEnded() { // TODO Auto-generated method stub
     * this.powerUp = Optional.empty(); }
     */
    @Override
    public int getNumberOfUsedPowerUps() {
        // TODO Auto-generated method stub
        return this.usedPowerUps;
    }

    @Override
    public boolean isMatchGoing() {
        // TODO Auto-generated method stub
        return !this.isMatchPaused;
    }

    @Override
    public void pauseMatch() {
        // TODO Auto-generated method stub
        if (this.isMatchGoing()) {
            this.isMatchPaused = true;
        }
    }

    @Override
    public void unpauseMatch() {
        // TODO Auto-generated method stub
        if (!this.isMatchGoing()) {
            this.isMatchPaused = false;
        }
    }

    @Override
    public int getCurrentRound() {
        // TODO Auto-generated method stub
        return this.currentRound;
    }

    @Override
    public void incrementRound() {
        // TODO Auto-generated method stub
        if (this.currentRound < this.MAX_OF_ROUNDS.getRounds() && !this.isMatchGoing()) {
            this.currentRound++;
        }
    }

    @Override
    public GameMode getMode() {
        // TODO Auto-generated method stub
        return this.GAME_MODE;
    }
    
    @Override
    public UnmodifiableMatchData unmodifiableCopy() {
        // TODO Auto-generated method stub
        return new UnmodifiableMatchData(new MatchDataImpl(this.GAME_MODE));
    }

}
