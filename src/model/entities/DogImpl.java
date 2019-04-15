package model.entities;

import java.util.Optional;

import audio.Sound;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.ModelImpl;
import model.properties.PositionImpl;
import model.properties.Velocity;
import model.properties.VelocityImpl;
import settings.SettingsImpl;
/**
 * 
 * This class represents the dog, animation etc..
 *
 */
public final class DogImpl extends AbstractCharacter implements Dog {
    /**
     * MAX POS Y, USED FOR DOG AND DUCKS.
     */
    public static final double FINAL_POS_Y = ModelImpl.GAME_HEIGHT * 0.70;
    private static final double INIT_POS_X = 0;
    private static final double INIT_POS_Y = ModelImpl.GAME_HEIGHT * 0.85;
    private static final double FINAL_POS_X = ModelImpl.GAME_WIDTH / 2;
    private static final double WIDTH = ModelImpl.GAME_WIDTH / 10;
    private static final double HEIGHT = ModelImpl.GAME_HEIGHT / 10;
    private static final double VELOCITY_X = ModelImpl.GAME_WIDTH / 12;
    private static final double VELOCITY_JUMP_Y = -ModelImpl.GAME_WIDTH / 5;
    private static final int WAITING_MILLIS = 750;
    private static final int UPDATE_MILLIS = 500;
    private static final int LAUGH_MILLIS = 1000;
    private static final int HAPPY_MILLIS = 750;

    private static final int DOG_HIT = 50;

    private DogStatus status;
    private Optional<Duck> lastDuck;
    private boolean firstTime;

    /**
     * 
     * @param shape
     *          shape of the dog
     * @param velocity
     *          initial of the dog
     */
    public DogImpl(final Shape shape, final Velocity velocity) {
        super(shape, velocity);
        this.status = DogStatus.RIGHT;
        this.lastDuck = Optional.empty();
        this.firstTime = true;
    }

    /**
     * 
     * Call the other constructor.
     * 
     */
    public DogImpl() {
        this(new Rectangle(INIT_POS_X, INIT_POS_Y, WIDTH, HEIGHT), 
             new VelocityImpl(VELOCITY_X, 0.0));
    }

    @Override
    public boolean isInGrass() {
        return this.status == DogStatus.IN_GRASS; 
    }

    private void inGrass() {
        this.status = DogStatus.IN_GRASS;
    }

    private void updateDogStatus(final int timeElapsed) {
        if (this.status == DogStatus.RIGHT || this.status == DogStatus.SNIFF) {
            super.addTimeElapsed(timeElapsed);
            if (super.getTimeElapsed() >= UPDATE_MILLIS) {
                super.addTimeElapsed(-UPDATE_MILLIS);
                this.setDogStatus(this.status == DogStatus.RIGHT ? DogStatus.SNIFF : DogStatus.RIGHT);
            }
        }

        if ((this.status == DogStatus.RIGHT 
           || this.status == DogStatus.SNIFF)
           && this.getPosition().getX() >= FINAL_POS_X) {
            this.setDogStatus(DogStatus.ATTENTION);
            super.resetTimeElapsed();
        } 

        if (this.status == DogStatus.ATTENTION) {
            super.addTimeElapsed(timeElapsed);
            this.setVelocity(new VelocityImpl(0, 0));
        }

        if (this.status == DogStatus.ATTENTION && super.getTimeElapsed() >= WAITING_MILLIS) {
            super.addTimeElapsed(timeElapsed);
            this.setVelocity(new VelocityImpl(0, VELOCITY_JUMP_Y));
            this.setDogStatus(DogStatus.JUMP);
        }

        if (this.status == DogStatus.JUMP && this.getPosition().getY() <= FINAL_POS_Y) {
            this.setVelocity(new VelocityImpl(0, 0));
            this.inGrass();
        }

        if (this.status == DogStatus.LAUGH) {
            super.addTimeElapsed(timeElapsed);
            final int time = this.firstTime ? LAUGH_MILLIS * 2 : LAUGH_MILLIS;
            if (super.getTimeElapsed() >= time) {
                super.addTimeElapsed(-time);
                this.inGrass();
                if (this.firstTime) {
                    this.firstTime = false;
                }
            }
        }

        if (this.status == DogStatus.HAPPY) {
            super.addTimeElapsed(timeElapsed);
            final int time = this.firstTime ? HAPPY_MILLIS * 2 : HAPPY_MILLIS;
            if (super.getTimeElapsed() >= time) {
                super.addTimeElapsed(-time);
                this.lastDuck = Optional.empty();
                this.setPosition(new PositionImpl(FINAL_POS_X, FINAL_POS_Y));
                this.inGrass();
                if (this.firstTime) {
                    this.firstTime = false;
                }
            }
        }
    }

    @Override
    public void kill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setStatus(final EntityStatus status) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(final int timeElapsed) {
        if (this.lastDuck.isPresent()) {
            if (this.lastDuck.get().getPosition().getY() >= FINAL_POS_Y) {
                this.setPosition(new PositionImpl(this.lastDuck.get().getPosition().getX(), this.getPosition().getY()));
                this.setDogStatus(DogStatus.HAPPY);
                if (SettingsImpl.getSettings().isBackgroundAudioOn()) {
                    Sound.CAPTURED_DUCK.play();
                }
            }
        }
        this.updateDogStatus(timeElapsed);
        super.update(timeElapsed);
    }

    @Override
    public void setDogStatus(final DogStatus status) {
        this.status = status;
    }

    @Override
    public DogStatus getDogStatus() {
        return this.status;
    }

    @Override
    public void setLastDuckKilled(final Duck duck) {
        this.lastDuck = Optional.of(duck);
    }

    @Override
    public Optional<Duck> getLastDuckKilled() {
        return this.lastDuck;
    }

    @Override
    public int getDogNegativeScore() {
        if (SettingsImpl.getSettings().isBackgroundAudioOn()) {
            Sound.DOG.play();
        }
        return DOG_HIT;
    }
}
