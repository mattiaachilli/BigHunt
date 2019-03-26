package controller.matches;

import settings.GlobalDifficulty;

/**
 * A story match.
 * 
 * @author simone
 *
 */
public class StoryMatch extends AbstractMatch {

    private final GameMode GAME_MODE;
    private final MaxOfRounds MAX_OF_ROUNDS;

    private MatchDifficulty difficulty;

    /**
     * 
     * @param globalDifficulty
     *          the difficulty selected from the main menu
     */
    public StoryMatch(final GlobalDifficulty globalDifficulty) {
        super();
        this.GAME_MODE = GameMode.STORY_MODE;
        this.MAX_OF_ROUNDS = MaxOfRounds.FIVE_ROUNDS;

        switch (globalDifficulty.getGlobalDifficulty()) {
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

    /**
     * 
     * @return the game mode
     */
    public GameMode getMode() {
        // TODO Auto-generated method stub
        return this.GAME_MODE;
    }

    /**
     * 
     * @return the difficulty
     */
    public MatchDifficulty getDifficulty() {
        // TODO Auto-generated method stub
        return this.difficulty;
    }

    /**
     * 
     * @return the number of rounds that a match can have.
     */
    public MaxOfRounds getMaxOfRounds() {
        // TODO Auto-generated method stub
        return this.MAX_OF_ROUNDS;
    }

    /**
     * 
     * @return true if the match is over
     */
    public boolean isMatchOver() {
        // TODO Auto-generated method stub
        return this.getCurrentRound() >= this.MAX_OF_ROUNDS.getRounds()
        || this.getMatchData().getGlobalScore() < this.difficulty.getLimitOfDifficulty() * this.getCurrentRound();
    }
}