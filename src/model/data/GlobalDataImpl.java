package model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GlobalDataImpl implements GlobalData {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int MAX_OF_HIGH_SCORES = 5;
    private static final Comparator<HighScore> COMPARATOR = (first, second) -> Integer
            .compare(second.getScore(), first.getScore());

    private List<HighScore> highScores;
    private List<PlayerData> players;
    
    public GlobalDataImpl() {
        this.highScores = Stream.generate( () -> new HighScoreImpl("---", 0))
                .limit(this.MAX_OF_HIGH_SCORES).collect(Collectors.toList());
        this.players = new ArrayList<PlayerData>();
    }
    
    @Override
    public List<HighScore> getHighScores() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableList(this.highScores);
    }

    @Override
    public boolean isHighScore(final int score) {
        // TODO Auto-generated method stub
        return this.highScores.size() < this.MAX_OF_HIGH_SCORES
                || score > this.highScores.get(MAX_OF_HIGH_SCORES).getScore();
    }

    @Override
    public List<PlayerData> getPlayers() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableList(this.players);
    }

    @Override
    public boolean isPresent(final String name) {
        // TODO Auto-generated method stub
        return !this.players.isEmpty() || this.players.stream().anyMatch(p -> p.getName().equals(name));
    }

    @Override
    public void addPlayer(final String name) {
        // TODO Auto-generated method stub
        if (!this.isPresent(name)) {
            this.players.add(new PlayerDataImpl(name));
        }
    }

    @Override
    public void addHighScore(final int score, final String name) {
        // TODO Auto-generated method stub
        if(this.isHighScore(score)) {
            this.highScores.add(new HighScoreImpl(name, score));
            if(this.highScores.size() > this.MAX_OF_HIGH_SCORES) {
                this.highScores.remove(this.highScores.stream().min(this.COMPARATOR).get());
            }
            this.highScores.sort(this.COMPARATOR);
        }
    }

    @Override
    public void addMatchData(final MatchData matchdata, final String playerName) {
        // TODO Auto-generated method stub
        this.addHighScore(matchdata.getGlobalScore(), playerName);
    }

}
