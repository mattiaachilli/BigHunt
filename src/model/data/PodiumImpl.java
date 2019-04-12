package model.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int MAX_OF_HIGH_SCORES = 5;
    private static final Comparator<HighScore> COMPARATOR = (o1, o2) -> {
        return o2.getScore() - o1.getScore();
    };

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
        return score > this.highScores.stream().max(COMPARATOR).get().getScore();
    }

    @Override
    public final void addHighScore(final int score, final String name) {
        if (this.isHighScore(score)) {
            this.highScores.add(new HighScoreImpl(name, score));
            if (this.highScores.size() > MAX_OF_HIGH_SCORES) {
                this.highScores.remove(this.highScores.stream().max(COMPARATOR).get());
            }
            this.highScores.sort(COMPARATOR);
        }
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
