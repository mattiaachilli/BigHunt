package model.data;

public class HighScoreImpl implements HighScore {
    
    private String name;
    private int score;
    
    public HighScoreImpl(final String name, final int score) {
        this.name = name;
        this.score = score;
    }
    
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return this.name;
    }

    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return this.score;
    }

}
