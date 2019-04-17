package model.data;

/**
 * The serializable implementation of an high score.
 */
public class HighScoreImpl implements HighScore {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final String name;
    private final int score;

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
    public final String getName() {
        return this.name;
    }

    @Override
    public final int getScore() {
        return this.score;
    }

}
