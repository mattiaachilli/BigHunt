import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import model.Model;
import model.ModelImpl;
import model.entities.Dog;
import model.entities.Duck;
import model.entities.Entity;
import settings.SettingsImpl;
import utility.Utilities;
import view.entities.EntityImageTypeImpl;
import javax.swing.JFrame;

import controller.converter.EntitiesConverter;

public class Game extends Canvas implements Runnable{

     /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final int PERIOD = 16; //60 FPS
     private volatile boolean running;
     private Thread th;
     private Model model;

     public void render(final int elapsed) {

         BufferStrategy bs = getBufferStrategy();
         if(bs == null) {
                 this.createBufferStrategy(3);
                 return;
         }
         Graphics2D g = (Graphics2D)bs.getDrawGraphics();
         g.setColor(Color.white);
         g.fillRect(0, 0, this.getWidth(), this.getHeight());
         for(Entity e: model.getEntities()) {
             EntityImageTypeImpl.getInstance().updateEntity(e, elapsed);
             EntitiesConverter.convertEntity(e).getPicture();
             if(e instanceof Duck) {
                 final Duck duck = (Duck)e;
                 duck.render(g);
                 /*
                 if(duck.hasPowerUp()) {
                     duck.getPowerUp().get().render(g);
                 }
                 */
             } else {
                 e.render(g);
             }
         }
         g.dispose();
         bs.show();
     }

     @Override
     public final void run() {
         long lastTime = System.currentTimeMillis();
         while (running && !model.isGameOver()) { /* Running and not gameover */
             final long current = System.currentTimeMillis();
             final int elapsed = (int) (current - lastTime);
            // processInput();
             this.model.update(elapsed);
             render(elapsed);
             Utilities.waitForNextFrame(PERIOD, current);
             lastTime = current;
         }
         if (model.isGameOver()) {
             System.out.println("GameOver!");
             System.exit(0);
         }
     }

     public synchronized void start() {
         if(running) {
                 return;
         } else {
                 running = true;
                 th = new Thread(this);
                 th.start();
         }
     }

    public Game(Model model) {
        this.model = model;
        Dimension size = new Dimension((int)SettingsImpl.getSettings().getSelectedResolution().getKey(), (int)SettingsImpl.getSettings().getSelectedResolution().getValue());
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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Game g = new Game(new ModelImpl());
        frame.add(g);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.start();
    }
}
