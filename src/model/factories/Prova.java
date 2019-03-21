package model.factories;

import java.util.Optional;

import model.entities.powerup.PowerUp;
import model.properties.Position;
import model.properties.PositionImpl;
import model.properties.Velocity;

public class Prova {

    public static void main(String[] args) {
        
        PowerUpFactory p = PowerUpFactoryImpl.getInstance();
        
        for (int i = 0; i < 10; i++) {
            Optional<PowerUp> powe = p.createRandomPowerUp(new PositionImpl(0, 0));
            System.out.println(powe.isPresent() ? powe.get().getType() : powe);
        }
        
    }
}
