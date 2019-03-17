package model.data;

/**
 * The serializable implementation of an high score.
 * @author simone
 */
public class HighScoreImpl implements HighScore {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    /**
     * Constructor of an high score.
     * @param name
     *          the user's username
     * @param score
     *          the new score
     */
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
