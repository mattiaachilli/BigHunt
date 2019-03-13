package controller.matches;

import settings.GlobalDifficulty;

public class SurvivalMatch extends AbstractMatch {

    private final GameMode GAME_MODE;
    private final MaxOfRounds MAX_OF_ROUNDS;
    
    private MatchDifficulty difficulty;
    
    public SurvivalMatch(final GlobalDifficulty globalDifficulty) {
        this.GAME_MODE = GameMode.SURVIVAL_MODE;
        this.MAX_OF_ROUNDS = MaxOfRounds.FIVE_ROUNDS;
        
        switch(globalDifficulty.getGlobalDifficulty()) {
        case "EASY":
            this.difficulty = MatchDifficulty.SURVIVAL_EASY;
            
        case "MEDIUM":
            this.difficulty = MatchDifficulty.SURVIVAL_MEDIUM;
            
        case "HARD":
            this.difficulty = MatchDifficulty.SURVIVAL_HARD;
            
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
                || this.matchdata.getFlownDucks() >= this.difficulty.getLimitOfDifficulty();
    }

    @Override
    public void incrementRound() {
        // TODO Auto-generated method stub
        if (this.currentRound < this.MAX_OF_ROUNDS.getRounds() && !this.isMatchGoing()) {
            this.currentRound++;
        }
    }
}
