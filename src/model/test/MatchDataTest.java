package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import model.data.MatchData;
import model.matches.StoryMatch;
import model.matches.SurvivalMatch;
import settings.GlobalDifficulty;

/**
 * Test for match data classes.
 * 
 * @author simone
 *
 */
public class MatchDataTest {

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
        assertEquals(this.survivalMatch.getCurrentRound(), 1);
        assertEquals(this.storyMatch.getCurrentRound(), 1);
        this.storyMatch.incrementRound();
        assertNotEquals(this.storyMatch.getCurrentRound(), 2);
        for (int i = 0; i < 3; i++) {
            this.survivalMatch.incrementRound();
            this.storyMatch.incrementRound();
        }
        assertEquals(this.survivalMatch.getCurrentRound(), 1);
        assertEquals(this.storyMatch.getCurrentRound(), 4);
        for (int i = 0; i < 3; i++) {
            this.survivalMatch.incrementRound();
            this.storyMatch.incrementRound();
        }
        assertEquals(this.survivalMatch.getCurrentRound(), 1);
        assertEquals(this.storyMatch.getCurrentRound(), FIVE);
    }

    /**
     * Tests the score.
     */
    @Test
    public final void testScore() {
        int score = 100;
        int deltaScore = FIFTY;
        this.storyMatch.getMatchData().incrementScoreOf(score);
        assertEquals(this.storyMatch.getMatchData().getGlobalScore(), score);
        score += deltaScore;
        this.storyMatch.getMatchData().incrementScoreOf(deltaScore);
        assertEquals(this.storyMatch.getMatchData().getGlobalScore(), score);
        deltaScore = TWENTY;
        score -= deltaScore;
        this.storyMatch.getMatchData().decrementScoreOf(deltaScore);
        assertEquals(this.storyMatch.getMatchData().getGlobalScore(), score);
        score += deltaScore;
        this.storyMatch.getMatchData().incrementScoreOf(deltaScore);
        assertEquals(this.storyMatch.getMatchData().getGlobalScore(), score);
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

    /**
     * Tests the unmodifiable copies of match datas.
     */
    @Test
    public final void testUnmodifiableData() {
        final MatchData unmodifiable = this.storyMatch.getMatchData().unmodifiableCopy();
        this.testUnsupported(() -> unmodifiable.incrementScoreOf(FIVE));
    }

}
