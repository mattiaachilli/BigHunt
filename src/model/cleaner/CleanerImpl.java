package model.cleaner;

import java.util.ArrayList;
import java.util.List;

import model.ModelImpl;
import model.entities.DogImpl;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.powerup.PowerUp;

/**
 * 
 * Implementation of the cleaner.
 *
 */
public class CleanerImpl implements Cleaner {

    @Override
    public final void clean(final List<Duck> ducks, final List<PowerUp> powerUps) {
        final List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < ducks.size(); i++) {
            if (ducks.get(i).getPosition().getY() < 0 || ducks.get(i).getPosition().getY() >= DogImpl.FINAL_POS_Y) {
                indexes.add(i);
            }
        }
        for (final Integer index: indexes) {
            ducks.remove((int) index);
        }

        indexes.clear();

        for (int i = 0; i < powerUps.size(); i++) {
            if (powerUps.get(i).isHit() 
                || powerUps.get(i).getPosition().getY() <= 0
                || powerUps.get(i).getPosition().getY() >= DogImpl.FINAL_POS_Y) {
                indexes.add(i);
            }
        }
        for (final Integer index: indexes) {
            powerUps.remove((int) index);
        }
    }
}
