package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import controller.matches.GameMode;
import model.data.MatchData;
import model.data.MatchDataImpl;

public class MatchDataTest {
    
    private final MatchData survivalMatch = new MatchDataImpl(GameMode.SURVIVAL_MODE);
    private final MatchData storyMatch = new MatchDataImpl(GameMode.STORY_MODE);
    
    /**
     * Tests the rounds related to the game mode
     */
    @Test
    public final void testRounds() {
        this.storyMatch.unpauseMatch();
        this.survivalMatch.unpauseMatch();
        assertEquals(this.survivalMatch.getCurrentRound(), 1);
        assertEquals(this.storyMatch.getCurrentRound(), 1);
        this.storyMatch.incrementRound();
        assertNotEquals(this.storyMatch.getCurrentRound(),2);
        this.storyMatch.pauseMatch();
        this.survivalMatch.pauseMatch();
        for(int i = 0; i < 3; i++) {
            this.survivalMatch.incrementRound();
            this.storyMatch.incrementRound();
        }
        assertEquals(this.survivalMatch.getCurrentRound(), 1);
        assertEquals(this.storyMatch.getCurrentRound(), 4);    
        for(int i = 0; i < 3; i++) {
            this.survivalMatch.incrementRound();
            this.storyMatch.incrementRound();
        }
        assertEquals(this.survivalMatch.getCurrentRound(), 1);
        assertEquals(this.storyMatch.getCurrentRound(), 5);  
    }
    
    /**
     * Tests the score
     */
    @Test
    public final void testScore() {
        int score = 100;
        int deltaScore = 50;
        this.storyMatch.unpauseMatch();
        this.storyMatch.incrementScoreOf(score);
        assertEquals(this.storyMatch.getGlobalScore(), score);
        score += deltaScore;
        this.storyMatch.incrementScoreOf(deltaScore);
        assertEquals(this.storyMatch.getGlobalScore(), score);
        deltaScore = 20;
        score -= deltaScore;
        this.storyMatch.decrementScoreOf(deltaScore);
        assertEquals(this.storyMatch.getGlobalScore(), score);
        this.storyMatch.pauseMatch();
        score += deltaScore;
        this.storyMatch.incrementScoreOf(deltaScore);
        assertNotEquals(this.storyMatch.getGlobalScore(), score);
    }
    
    /**
     * Tests the pauses
     * The match starts with a pause in order to allow the dog to dive in the grass
     */
    @Test
    public final void testPause() {
        assertFalse(this.survivalMatch.isMatchGoing());
        this.survivalMatch.unpauseMatch();
        assertTrue(this.survivalMatch.isMatchGoing());
        this.survivalMatch.pauseMatch();
        assertFalse(this.survivalMatch.isMatchGoing());
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
    
    @Test
    public final void testUnmodifiableData() {
        final MatchData unmodifiable = this.storyMatch.unmodifiableCopy();
        this.testUnsupported( () -> unmodifiable.incrementScoreOf(5));
    }
    
}
