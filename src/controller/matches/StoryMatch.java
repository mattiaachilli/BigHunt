package controller.matches;

import model.data.MatchData;
import model.data.MatchDataImpl;
import settings.GlobalDifficulty;

public class StoryMatch implements Match {

    private static final int FIRST_ROUND = 1;
    private static final boolean START_PAUSED = true;
    
    private final GameMode GAME_MODE;
    private final MaxOfRounds MAX_OF_ROUNDS;
    
    private MatchDifficulty difficulty;
    private MatchData matchdata;
    private int currentRound;
    private boolean isMatchPaused;
    
    public StoryMatch(final GlobalDifficulty globalDifficulty) {
        this.GAME_MODE = GameMode.STORY_MODE;
        this.MAX_OF_ROUNDS = MaxOfRounds.FIVE_ROUNDS;
        
        this.matchdata = new MatchDataImpl();
        this.currentRound = FIRST_ROUND;
        this.isMatchPaused = START_PAUSED;
        
        switch(globalDifficulty.getGlobalDifficulty()) {
        case "EASY":
            this.difficulty = MatchDifficulty.STORY_EASY;
            
        case "MEDIUM":
            this.difficulty = MatchDifficulty.STORY_MEDIUM;
            
        case "HARD":
            this.difficulty = MatchDifficulty.STORY_HARD;
            
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public GameMode getMode() {
        // TODO Auto-generated method stub
        return this.GAME_MODE;
    }

    @Override
    public MatchDifficulty getDifficulty() {
        // TODO Auto-generated method stub
        return this.difficulty;
    }

    @Override
    public MatchData getMatchData() {
        // TODO Auto-generated method stub
        return this.matchdata;
    }

    @Override
    public boolean isMatchOver() {
        // TODO Auto-generated method stub
        return this.currentRound >= this.MAX_OF_ROUNDS.getRounds()
                || this.matchdata.getGlobalScore() < this.difficulty.getLimitOfDifficulty() * this.currentRound;
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
    

}
