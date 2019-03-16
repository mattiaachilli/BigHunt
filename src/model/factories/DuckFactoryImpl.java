package model.factories;

import javafx.scene.shape.Shape;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.decorator.YellowDuck;
import model.entities.Duck;
import model.entities.StandardDuck;
import model.properties.DuckDirection;
import model.properties.Velocity;

/**
 * 
 * Implementation of DuckFactory.
 *
 */

public class DuckFactoryImpl implements DuckFactory {

    @Override
    public final StandardDuck createStandardDuck(final Shape shape, final Velocity velocity, final DuckDirection direction) {
        return new StandardDuck(shape, velocity, direction);
    }

    @Override
    public final YellowDuck createYellowDuck(final Duck duck) {
        return new YellowDuck(duck);
    }

    @Override
    public final OrangeDuck createOrangeDuck(final Duck duck) {
        return new OrangeDuck(duck);
    }

    @Override
    public final PinkDuck createPinkDuck(final Duck duck) {
        return new PinkDuck(duck);
    }
}
