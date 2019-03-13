package controller.matches;

import model.data.MatchData;
import model.data.MatchDataImpl;

public abstract class AbstractMatch implements Match {
    
    private static final int FIRST_ROUND = 1;
    private static final boolean START_PAUSED = true;

    protected MatchData matchdata;
    protected int currentRound;
    protected boolean isMatchPaused;
    
    public AbstractMatch() {        
        this.matchdata = new MatchDataImpl();
        this.currentRound = FIRST_ROUND;
        this.isMatchPaused = START_PAUSED;
    }

    @Override
    public MatchData getMatchData() {
        // TODO Auto-generated method stub
        return this.matchdata;
    }
    
    @Override
    public int getCurrentRound() {
        // TODO Auto-generated method stub
        return this.currentRound;
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
