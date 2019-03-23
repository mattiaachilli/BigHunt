package model.cleaner;

import java.util.ArrayList;
import java.util.List;

import model.ModelImpl;
import model.entities.powerup.PowerUp;

/**
 * 
 * Implementation of the cleaner.
 *
 */
public class CleanerImpl implements Cleaner {

    @Override
    public final void cleanPowerUp(final List<PowerUp> powerUp) {
        final List<Integer> indexesPowUp = new ArrayList<>();
        for (int i = 0; i < powerUp.size(); i++) {
            if (powerUp.get(i).isHit() 
                || powerUp.get(i).getPosition().getY() <= 0
                || powerUp.get(i).getPosition().getY() >= ModelImpl.GAME_HEIGHT) {
                indexesPowUp.add(i);
            }
        }
        for (final Integer index: indexesPowUp) {
            powerUp.remove((int) index);
        }
    }
}
