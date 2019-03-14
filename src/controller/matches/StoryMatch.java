package controller.matches;

import settings.GlobalDifficulty;

public class StoryMatch extends AbstractMatch {
    
    private final GameMode GAME_MODE;
    private final MaxOfRounds MAX_OF_ROUNDS;
    
    private MatchDifficulty difficulty;
    
    public StoryMatch(final GlobalDifficulty globalDifficulty) {
        super();
        this.GAME_MODE = GameMode.STORY_MODE;
        this.MAX_OF_ROUNDS = MaxOfRounds.FIVE_ROUNDS;
        
        switch(globalDifficulty.getGlobalDifficulty()) {
        case "EASY":
            this.difficulty = MatchDifficulty.STORY_EASY;
            break;
            
        case "MEDIUM":
            this.difficulty = MatchDifficulty.STORY_MEDIUM;
            break;
            
        case "HARD":
            this.difficulty = MatchDifficulty.STORY_HARD;
            break;
            
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
    public boolean isMatchOver() {
        // TODO Auto-generated method stub
        return this.currentRound >= this.MAX_OF_ROUNDS.getRounds()
                || this.matchdata.getGlobalScore() < this.difficulty.getLimitOfDifficulty() * this.currentRound;
    }

    @Override
    public void incrementRound() {
        // TODO Auto-generated method stub
        if (this.currentRound < this.MAX_OF_ROUNDS.getRounds() && !this.isMatchGoing()) {
            this.currentRound++;
        }
    }
}
