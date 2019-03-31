package model.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * This enum represents all possible direction of a duck.
 *
 */
public enum DuckDirection {
    /**
     * RIGHT DIRECTION.
     */
    RIGHT(1), 
    /**
     * LEFT DIRECTION.
     */
    LEFT(2), 
    /**
     * RIGHT UP DIRECTION.
     */
    RIGHT_UP(3), 
    /**
     * LEFT UP DIRECTION.
     */
    LEFT_UP(4), 
    /**
     * RIGHT DOWN DIRECTION.
     */
    RIGHT_DOWN(5), 
    /**
     * LEFT DOWN DIRECTION.
     */
    LEFT_DOWN(6), 
    /**
     * FLOWN AWAY(UP) DIRECTION.
     */
    FLOWN_AWAY(7), 
    /**
     * KILLED(DOWN) DIRECTION.
     */
    KILLED(8),
    /**
     * PRECIPITATE(DOWN) DIRECTION.
     */
    PRECIPITATE(9);

    private final int codeId;

    DuckDirection(final int codeId) {
        this.codeId = codeId;
    }

    /**
     * Get the code direction(used to generate direction).
     * 
     * @return the code direction
     */
    public int getCodeId() {
        return this.codeId;
    }

    /**
     * 
     * @return the opponent direction to the actual
     */
    public Optional<DuckDirection> getOpponentPosition() {
        Optional<DuckDirection> opponent = Optional.empty();
        if (this == RIGHT) {
            opponent = Optional.of(LEFT);
        } else if (this == LEFT) {
            opponent = Optional.of(RIGHT);
        } else if (this == RIGHT_UP) {
            opponent = Optional.of(LEFT_UP);
        } else if (this == LEFT_UP) {
            opponent = Optional.of(RIGHT_UP);
        } else if (this == RIGHT_DOWN) {
            opponent = Optional.of(LEFT_DOWN);
        } else if (this == LEFT_DOWN) {
            opponent = Optional.of(RIGHT_DOWN);
        } 
        return opponent;
    }

    /**
     * 
     * @return the list of random directions.
     */
    public static List<Pair<DuckDirection, Integer>> getRandomDirection() {
        final List<Pair<DuckDirection, Integer>> listRandomDirection = new ArrayList<>();
        listRandomDirection.add(new ImmutablePair<>(RIGHT, RIGHT.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(LEFT, LEFT.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(RIGHT_UP, RIGHT_UP.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(LEFT_UP, LEFT_UP.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(RIGHT_DOWN, RIGHT_DOWN.getCodeId()));
        listRandomDirection.add(new ImmutablePair<>(LEFT_DOWN, LEFT_DOWN.getCodeId()));
        return listRandomDirection;
    }
}
