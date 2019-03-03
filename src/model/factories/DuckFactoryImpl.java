package model.factories;

import javafx.scene.shape.Shape;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.decorator.YellowDuck;
import model.entities.Duck;
import model.entities.StandardDuck;
import model.properties.Velocity;

public class DuckFactoryImpl implements DuckFactory {
   
    @Override
    public Duck createRandomDuck(Shape shape, Velocity velocity) {
       return null;
    }

    @Override
    public StandardDuck createStandardDuck(Shape shape, Velocity velocity) {
        return new StandardDuck(shape, velocity);
    }

    @Override
    public YellowDuck createYellowDuck(Duck duck) {
        return new YellowDuck(duck);
    }

    @Override
    public OrangeDuck createOrangeDuck(Duck duck) {
        return new OrangeDuck(duck);
    }

    @Override
    public PinkDuck createPinkDuck(Duck duck) {
        return new PinkDuck(duck);
    }
}
