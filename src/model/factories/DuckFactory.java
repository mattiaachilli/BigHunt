package model.factories;

import javafx.scene.shape.Shape;
import model.decorator.OrangeDuck;
import model.decorator.PinkDuck;
import model.decorator.YellowDuck;
import model.entities.Duck;
import model.entities.StandardDuck;
import model.properties.Velocity;

/**
 * 
 * Interface that represents a duck factory.
 * 
 */
public interface DuckFactory {
    
    /**
     * 
     * @param shape
     *          of the duck.
     * @param velocity
     *          of the duck.
     * @return a random duck created by percentage.
     */
    Duck createRandomDuck(Shape shape, Velocity velocity);
    
    /**
     * 
     * @param shape
     *          of the duck.
     * @param velocity
     *          of the duck.
     * @return a standard duck.
     */
    StandardDuck createStandardDuck(Shape shape, Velocity velocity);
    
    /**
     * 
     * @param duck 
     *          decorated.
     * @return a yellow duck.
     */
    YellowDuck createYellowDuck(Duck duck);
    
    /**
     * 
     * @param duck 
     *          decorated.
     * @return a orange duck.
     */
    OrangeDuck createOrangeDuck(Duck duck);
    
    /**
     * 
     * @param duck 
     *          decorated.
     * @return a pink duck.
     */
    PinkDuck createPinkDuck(Duck duck);
}