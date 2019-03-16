package model.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.ModelImpl;
import model.properties.Velocity;
import model.properties.VelocityImpl;

/**
 * 
 * This class represents the dog, animation etc..
 *
 */
public final class DogImpl extends AbstractEntity implements Dog {
    private static final double INIT_POS_X = 0;
    private static final double INIT_POS_Y = ModelImpl.GAME_HEIGHT / 2 + ModelImpl.GAME_HEIGHT / 3;
    private static final double FINAL_POS_X = ModelImpl.GAME_WIDTH / 2;
    private static final double FINAL_POS_Y = ModelImpl.GAME_HEIGHT / 2 + ModelImpl.GAME_HEIGHT / 4 - 100;
    private static final double WIDTH = 70;
    private static final double HEIGHT = 50;
    private static final double VELOCITY_X = 100.0;
    private static final double VELOCITY_JUMP_Y = -200.0;
    private static final int WAITING_MILLIS = 500;
    private static final int UPDATE_MILLIS = 1000;
    private DogStatus status;
    private boolean inGrass;
    private int waitingTime;
    /**
     * 
     * @param shape
     *          shape of the dog
     * @param velocity
     *          initial of the dog
     */
    public DogImpl(final Shape shape, final Velocity velocity) {
        super(shape, velocity);
        this.inGrass = false;
        this.status = DogStatus.RIGHT;
        this.waitingTime = 0;
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
        return this.inGrass; 
    }

    @Override
    public void inGrass() {
        this.inGrass = true;
    }

    @Override
    public void render(final Graphics2D g) {
        g.setColor(Color.green);
        g.fill(new Rectangle2D.Double(this.getPosition().getX(), this.getPosition().getY(), 70, 50));
    }

    private void updateVelocityStatus(final int timeElapsed) {
        if (this.status == DogStatus.RIGHT || this.status == DogStatus.SNIFF) {
            this.waitingTime += timeElapsed;
            if (this.waitingTime >= UPDATE_MILLIS) {
                this.waitingTime -= UPDATE_MILLIS;
                this.status = this.status == DogStatus.RIGHT ? DogStatus.SNIFF : DogStatus.RIGHT;
            }
        }

        if ((this.status == DogStatus.RIGHT 
           || this.status == DogStatus.SNIFF)
           && this.getPosition().getX() >= FINAL_POS_X) {
            this.status = DogStatus.ATTENTION;
            this.waitingTime = 0;
        } 

        if (this.status == DogStatus.ATTENTION) {
            this.waitingTime += timeElapsed;
            this.setVelocity(new VelocityImpl(0, 0));
        }

        if (this.status == DogStatus.ATTENTION && this.waitingTime >= WAITING_MILLIS) {
            this.waitingTime -= WAITING_MILLIS;
            this.setVelocity(new VelocityImpl(0, VELOCITY_JUMP_Y));
            this.status = DogStatus.JUMP;
        }

        if (this.status == DogStatus.JUMP && this.getPosition().getY() <= FINAL_POS_Y) {
            this.setVelocity(new VelocityImpl(0, 0));
            this.status = DogStatus.EMPTY;
        }
    }

    @Override
    public void update(final int timeElapsed) {
        this.updateVelocityStatus(timeElapsed);
        super.update(timeElapsed);
    }

    @Override
    public DogStatus getDogStatus() {
        return this.status;
    }
}
