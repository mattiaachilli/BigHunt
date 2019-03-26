package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import model.Model;
import model.ModelImpl;
import model.entities.Duck;
import model.entities.DuckProperty;
import model.entities.Entity;
import model.entities.StandardDuck;
import model.properties.DuckDirection;
import model.properties.Velocity;
import model.properties.VelocityImpl;
import model.spawner.duck.SpawnSide;
import utility.Utilities;

public class GameView extends JPanel implements MouseMotionListener,Runnable {

    /**
     * 
     */
    private static final long serialVersionUID = 7192464009733502537L;
    
    private Image cursorImg;
    private java.awt.Rectangle cursorRectangle;
    
    private final Velocity standardVelocity = new VelocityImpl(DuckProperty.STANDARD_DUCK.getVelocity(), DuckProperty.STANDARD_DUCK.getVelocity());
    private final DuckDirection direction = SpawnSide.getSpawnSide();
    private Duck duck = new StandardDuck(new Rectangle(200.0, 200.0, StandardDuck.WIDTH_DUCK, StandardDuck.HEIGHT_DUCK), standardVelocity, direction);
    
    public GameView(Model model) {
        cursorImg = Toolkit.getDefaultToolkit().getImage("C:/Users/giuli/Desktop/UNI/oop/PROGETTO/oop18-achilli-mattia-bernardini-yuri-dulja-giulio-romagnoli/res/view/weapon/gunsight.png");
        Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(this.getX(), this.getY()), "img");
        this.setLayout(new BorderLayout());
        Dimension size = new Dimension((int)1366, (int)1000);
        this.setSize(size);
        this.addMouseMotionListener(this);
        this.setCursor(c);
        cursorRectangle = new java.awt.Rectangle();
        cursorRectangle.setSize(cursorImg.getWidth(null), cursorImg.getHeight(null));
        cursorRectangle.setLocation(- cursorImg.getWidth(null), - cursorImg.getHeight(null));
        this.addMouseListener(new MouseAdapter() {
            public void MousePressed(MouseEvent e) {
                final int x = e.getX();
                final int y = e.getY();
                final Point2D hitPoint = new Point2D(x, y);
                if(duck != null) {
                    if(duck.getShape().contains(hitPoint)) {
                        duck.kill();
                    }
                }
            }
        });
    }
    

    @Override
    public void mouseDragged(MouseEvent e) {
        //
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        cursorRectangle.x = e.getPoint().x - cursorRectangle.width / 2;
        cursorRectangle.y = e.getPoint().y - cursorRectangle.height / 2;
        repaint();
    }
    
}
