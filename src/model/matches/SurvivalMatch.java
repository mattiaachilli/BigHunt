package model.matches;

import org.apache.commons.lang3.tuple.ImmutablePair;

import settings.GlobalDifficulty;

/**
 * A survival match with continuous spawning ducks.
 * 
 * @author simone
 *
 */
public class SurvivalMatch extends AbstractMatch {

    private static final GameMode GAME_MODE = GameMode.SURVIVAL_MODE;
    private static final MaxOfRounds MAX_OF_ROUNDS = MaxOfRounds.UNIQUE_ROUND;

    private MatchDifficulty difficulty;

    /**
     * 
     * @param globalDifficulty
     *          the difficulty selected from the main menu
     */
    public SurvivalMatch(final GlobalDifficulty globalDifficulty) {
        super();

        switch (globalDifficulty.getGlobalDifficulty()) {
        case "EASY":
            this.difficulty = MatchDifficulty.SURVIVAL_EASY;
            break;

        case "MEDIUM":
            this.difficulty = MatchDifficulty.SURVIVAL_MEDIUM;
            break;

        case "HARD":
            this.difficulty = MatchDifficulty.SURVIVAL_HARD;
            break;

        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * 
     * @return the game mode.
     */
    public GameMode getMode() {
        return SurvivalMatch.GAME_MODE;
    }

    /**
     * 
     * @return the difficulty.
     */
    public MatchDifficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * 
     * @return the number of rounds that a match can have.
     */
    public MaxOfRounds getMaxOfRounds() {
        return SurvivalMatch.MAX_OF_ROUNDS;
    }

    /**
     * 
     * @return true of the match is over
     */
    public boolean isMatchOver() {
        return this.getMatchData().getFlownDucks() >= this.difficulty.getLimitOfDifficulty();
    }

    @Override
    public final ImmutablePair<Integer, Integer> getRounds() {
        return new ImmutablePair<>(this.getCurrentRound(), this.getCurrentRound());
    }
}
