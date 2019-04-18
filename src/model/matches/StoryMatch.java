package model.matches;

import org.apache.commons.lang3.tuple.ImmutablePair;

import settings.GlobalDifficulty;

/**
 * A story match.
 *
 */
public class StoryMatch extends AbstractMatch {

    private static final GameMode GAME_MODE = GameMode.STORY_MODE;
    private static final MaxOfRounds MAX_OF_ROUNDS = MaxOfRounds.FIVE_ROUNDS;

    /**
     * The maximum of magazines that are given in the story mode.
     */
    public static final int MAX_MAGAZINES = 20;

    private final MatchDifficulty difficulty;

    /**
     * 
     * @param globalDifficulty
     *          the difficulty selected from the main menu
     */
    public StoryMatch(final GlobalDifficulty globalDifficulty) {
        super();

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
        return StoryMatch.GAME_MODE;
    }

    /**
     * 
     * @return the difficulty
     */
    public MatchDifficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * 
     * @return the number of rounds that a match can have.
     */
    public MaxOfRounds getMaxOfRounds() {
        return StoryMatch.MAX_OF_ROUNDS;
    }

    /**
     * 
     * @return true if the match is over
     */
    public boolean isMatchOver() {
        return this.isRoundEnded() && this.getMatchData().getGlobalScore() < this.difficulty.getLimitOfDifficulty() * this.getCurrentRound()
        || this.getCurrentMagazineNumber() > MAX_MAGAZINES
        || this.getCurrentMagazine().getAmmo() == 0 && this.getCurrentMagazineNumber() == MAX_MAGAZINES;
    }

    @Override
    public final ImmutablePair<Integer, Integer> getRounds() {
        return new ImmutablePair<>(this.getCurrentRound(), this.getMaxOfRounds().getRounds());
    }
}
