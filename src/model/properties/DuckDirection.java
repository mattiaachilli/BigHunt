package model.properties;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * This enum represents all possible direction of a duck.
 *
 */

public enum DuckDirection {
    UP(1),
    DOWN(2), 
    RIGHT(3), 
    LEFT(4), 
    RIGHT_UP(5), 
    LEFT_UP(6), 
    RIGHT_DOWN(7), 
    LEFT_DOWN(8), 
    FLOWN_AWAY(9), 
    KILLED(10); 
    
    private final int codeId;
    
    DuckDirection(final int codeId) {
        this.codeId = codeId;
    }
    
    public int getCodeId() {
        return this.codeId;
    }
    
    public static List<Pair<DuckDirection, Integer>> getRandomDirection() {
        final List<Pair<DuckDirection, Integer>> listRandomDirection = new ArrayList<>();
        listRandomDirection.add(new ImmutablePair<>(UP, UP.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(DOWN, DOWN.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(RIGHT, RIGHT.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(LEFT, LEFT.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(RIGHT_UP, RIGHT_UP.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(LEFT_UP, LEFT_UP.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(RIGHT_DOWN, RIGHT_DOWN.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(LEFT_DOWN, LEFT_DOWN.getCodeId()));
        return listRandomDirection;
    }
}
