import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import model.*;
import model.entities.Duck;
import model.entities.Entity;
import utility.Utilities;

import javax.swing.JFrame;

import javafx.geometry.Point2D;

/**
 * 
 * Testing class for shooting method.
 *
 */
public class Game extends Canvas implements Runnable {

     /**
     * 
     */
    private static final long serialVersionUID = 2626934652059141078L;
    private static final int PERIOD = 16; //60 FPS
    //private static final int FPS = 60;

     private volatile boolean running;
     private Thread th;
     private Model model;

     /**
      * .
      */
     public void render() {
         BufferStrategy bs = getBufferStrategy();
         if (bs == null) {
             this.createBufferStrategy(3);
             return;
         }
         Graphics2D g = (Graphics2D) bs.getDrawGraphics();
         g.setColor(Color.white);
         g.fillRect(0, 0, this.getWidth(), this.getHeight());
         for (Entity e: model.getEntities()) {
             e.render(g);
         }
         g.dispose();
         bs.show();
     }

     @Override
     public void run() {
         long lastTime = System.currentTimeMillis();
         while (running) { /* Running and not gameover */
             final long current = System.currentTimeMillis();
             final int elapsed = (int) (current - lastTime);
            // processInput();
             this.model.update(elapsed);
             render();
             Utilities.waitForNextFrame(PERIOD, current);
             lastTime = current;
         }
     }

     /**
      * start.
      */
     public synchronized void start() {
         if (running) {
                 return;
         } else {
                 running = true;
                 th = new Thread(this);
                 th.start();
         }
     }
/**
 * Constructor.
 * 
 * @param model the world
 */
    public Game(final Model model) {
        this.model = model;
        Dimension size = new Dimension((int) 1366, (int) 1000);
        this.setPreferredSize(size);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) { 
                for (Duck d : model.getDucks()) {
                    if (d.getShape().contains(e.getX(), e.getY())) {
                        d.kill();
                    }
                }
            }
        });
        setMaximumSize(size);
        setMinimumSize(size);
    }

    /**
     * main.
     * 
     * @param args strings.
     */
    public static void main(final String[] args) {
        JFrame frame = new JFrame();
        Game g = new Game(new ModelImpl(null));
        frame.add(g);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.start();
    }

}
