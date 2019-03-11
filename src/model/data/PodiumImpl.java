package model.data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PodiumImpl implements Podium {

    private static final int MAX_OF_HIGH_SCORES = 5;
    private static final Comparator<HighScore> COMPARATOR = (first, second) -> Integer
            .compare(second.getScore(), first.getScore());

    private List<HighScore> highScores;
    
    public PodiumImpl() {
        this.highScores = Stream.generate( () -> new HighScoreImpl("---", 0))
        .limit(MAX_OF_HIGH_SCORES).collect(Collectors.toList());
    }
    
    @Override
    public List<HighScore> getHighScores() {
        // TODO Auto-generated method stub
        return this.highScores;
    }

    @Override
    public boolean isHighScore(int score) {
        // TODO Auto-generated method stub
        return this.highScores.size() < MAX_OF_HIGH_SCORES
        || score > this.highScores.get(MAX_OF_HIGH_SCORES).getScore();
    }
    
    @Override
    public void addHighScore(int score, String name) {
        // TODO Auto-generated method stub
        if(this.isHighScore(score)) {
            this.highScores.add(new HighScoreImpl(name, score));
            if(this.highScores.size() > MAX_OF_HIGH_SCORES) {
                this.highScores.remove(this.highScores.stream().min(COMPARATOR).get());
            }
            this.highScores.sort(COMPARATOR);
        }
    }

}
