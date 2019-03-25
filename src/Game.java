import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import model.*;
import model.entities.Dog;
import model.entities.DogStatus;
import model.entities.Duck;
import model.entities.Entity;
import settings.SettingsImpl;
import utility.Utilities;
import view.entities.EntityImageType;
import view.entities.EntityImageTypeImpl;
import view.entities.ViewEntity;

import javax.swing.JFrame;

import controller.utilities.EntitiesConverter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Game extends Canvas implements Runnable{
    
     private final static int PERIOD = 16; //60 FPS
            
     private volatile boolean running;
     private Thread th;
     private Model model;
     private final EntityImageType eit = new EntityImageTypeImpl();
     
     
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
             eit.updateEntity(e, elapsed);
             if(e instanceof Dog) {
                 System.out.println(EntitiesConverter.convertEntity(e));
             }
             /*
             if(e instanceof Duck) {
                 final Duck duck = (Duck)e;
                 duck.render(g);
                 if(duck.hasPowerUp()) {
                     duck.getPowerUp().get().render(g);
                 }
             } else {
                 e.render(g);
             }*/
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
