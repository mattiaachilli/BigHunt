package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import model.data.MatchData;
import model.matches.StoryMatch;
import model.matches.SurvivalMatch;
import settings.GlobalDifficulty;

/**
 * Test for match data classes.
 *
 */
public class MatchDataTest {

    private static final String ERROR_MESSAGE = "Error";
    private static final int FIVE = 5;
    private static final int FIFTY = 50;
    private static final int TWENTY = 20;
    private final SurvivalMatch survivalMatch = new SurvivalMatch(GlobalDifficulty.EASY);
    private final StoryMatch storyMatch = new StoryMatch(GlobalDifficulty.EASY);

    /**
     * Tests the rounds related to the game mode.
     */
    @Test
    public final void testRounds() {
        assertEquals(ERROR_MESSAGE, this.survivalMatch.getCurrentRound(), 1);
        assertEquals(ERROR_MESSAGE, this.storyMatch.getCurrentRound(), 1);
        for (int i = 0; i < 3; i++) {
            this.survivalMatch.incrementRound();
            this.storyMatch.incrementRound();
        }
        assertEquals(ERROR_MESSAGE, this.survivalMatch.getCurrentRound(), 1);
        assertEquals(ERROR_MESSAGE, this.storyMatch.getCurrentRound(), 4);
        for (int i = 0; i < 3; i++) {
            this.survivalMatch.incrementRound();
            this.storyMatch.incrementRound();
        }
        assertEquals(ERROR_MESSAGE, this.survivalMatch.getCurrentRound(), 1);
        assertEquals(ERROR_MESSAGE, this.storyMatch.getCurrentRound(), FIVE);
    }

    /**
     * Tests the score.
     */
    @Test
    public final void testScore() {
        int score = 100;
        int deltaScore = FIFTY;
        this.storyMatch.getMatchData().incrementScoreOf(score);
        assertEquals(ERROR_MESSAGE, this.storyMatch.getMatchData().getGlobalScore(), score);
        score += deltaScore;
        this.storyMatch.getMatchData().incrementScoreOf(deltaScore);
        assertEquals(ERROR_MESSAGE, this.storyMatch.getMatchData().getGlobalScore(), score);
        deltaScore = TWENTY;
        score -= deltaScore;
        this.storyMatch.getMatchData().decrementScoreOf(deltaScore);
        assertEquals(ERROR_MESSAGE, this.storyMatch.getMatchData().getGlobalScore(), score);
        score += deltaScore;
        this.storyMatch.getMatchData().incrementScoreOf(deltaScore);
        assertEquals(ERROR_MESSAGE, this.storyMatch.getMatchData().getGlobalScore(), score);
    }

    /**
     * Tests the unmodifiable copies of match datas.
     */
    @Test
    public final void testUnmodifiableData() {
        final int lastRound = this.storyMatch.getCurrentRound();
        final MatchData unmodifiable = this.storyMatch.getMatchData().unmodifiableCopy();
        this.testUnsupported(() -> unmodifiable.incrementScoreOf(FIVE));
        assertEquals(ERROR_MESSAGE, this.storyMatch.getCurrentRound(), lastRound);
    }

    private void testUnsupported(final Runnable runnable) {
        try {
            runnable.run();
            fail("UnsupportedOperationException not catched");
        } catch (UnsupportedOperationException exception) {
            System.out.print("");
        } catch (Exception e) {
            fail("UnsupportedOperationException not catched");
        }
    }


}
