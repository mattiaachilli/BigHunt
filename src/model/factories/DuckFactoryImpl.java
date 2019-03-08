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
    public Duck createRandomDuck(final Shape shape, final Velocity velocity) {
       return null;
    }

    @Override
    public StandardDuck createStandardDuck(final Shape shape, final Velocity velocity, final DuckDirection direction) {
        return new StandardDuck(shape, velocity, direction);
    }

    @Override
    public YellowDuck createYellowDuck(final Duck duck) {
        return new YellowDuck(duck);
    }

    @Override
    public OrangeDuck createOrangeDuck(final Duck duck) {
        return new OrangeDuck(duck);
    }

    @Override
    public PinkDuck createPinkDuck(final Duck duck) {
        return new PinkDuck(duck);
    }
}
