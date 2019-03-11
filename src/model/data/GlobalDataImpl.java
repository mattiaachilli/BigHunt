package model.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobalDataImpl implements GlobalData {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Podium podium;
    private List<UserData> users;
    
    public GlobalDataImpl() {
        this.podium = new PodiumImpl();
        this.users = new ArrayList<UserData>();
    }
    
    @Override
    public List<HighScore> getHighScores() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableList(this.podium.getHighScores());
    }

    @Override
    public List<UserData> getUsers() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableList(this.users);
    }

    @Override
    public boolean isPresent(final String name) {
        // TODO Auto-generated method stub
        return !this.users.isEmpty() || this.users.stream().anyMatch(p -> p.getName().equals(name));
    }

    @Override
    public boolean isHighScore(final int score) {
        // TODO Auto-generated method stub
        return this.podium.isHighScore(score);
    }
    
    @Override
    public void addPlayer(final String name) {
        // TODO Auto-generated method stub
        if (!this.isPresent(name)) {
            this.users.add(new UserDataImpl(name));
        }
    }

    @Override
    public void addHighScore(final int score, final String name) {
        // TODO Auto-generated method stub
        this.podium.addHighScore(score, name);
    }

    @Override
    public void addMatchData(final MatchData matchdata, final String playerName) {
        // TODO Auto-generated method stub
        this.addHighScore(matchdata.getGlobalScore(), playerName);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

}
