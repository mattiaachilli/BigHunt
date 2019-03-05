package model.data;

import java.util.Optional;
import java.util.Set;

public class AchievementImpl implements Achievement {
    
    private final AchievementType type;
    private int value;
    
    public AchievementImpl(final AchievementType type, int value) {
        this.type = type;
        this.value = value;
    }
    
    @Override
    public AchievementType getAchievementType() {
        // TODO Auto-generated method stub
        return this.type;
    }

    @Override
    public Set<Integer> getAllTargets() {
        // TODO Auto-generated method stub
        return this.type.getTargets();
    }

    @Override
    public Optional<Integer> getNextTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getCurrentValueOfAchievement() {
        // TODO Auto-generated method stub
        return this.value;
    }

}
