package model.data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of a podium.
 * 
 * @author simone
 *
 */
public class PodiumImpl implements Podium {

    private static final int MAX_OF_HIGH_SCORES = 5;
    private static final Comparator<HighScore> COMPARATOR = (first, second) -> Integer.compare(second.getScore(),
    first.getScore());

    private List<HighScore> highScores;

    /**
     * Constructor of a podium.
     */
    public PodiumImpl() {
        this.highScores = Stream.generate(() -> new HighScoreImpl("---", 0)).limit(MAX_OF_HIGH_SCORES)
        .collect(Collectors.toList());
    }

    @Override
    public final List<HighScore> getHighScores() {
        return this.highScores;
    }

    @Override
    public final boolean isHighScore(final int score) {
        return score > this.highScores.get(MAX_OF_HIGH_SCORES - 1).getScore();
    }

    @Override
    public final void addHighScore(final int score, final String name) {
        if (this.isHighScore(score)) {
            this.highScores.add(new HighScoreImpl(name, score));
            if (this.highScores.size() > MAX_OF_HIGH_SCORES) {
                this.highScores.remove(this.highScores.stream().min(COMPARATOR).get());
            }
            this.highScores.sort(COMPARATOR);
        }
    }
}
