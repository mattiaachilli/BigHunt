package view.aimTest;


import javax.swing.JFrame;

import model.ModelImpl;
import view.GameView;

public class GamePanel extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6145406201612841177L;
    
    private GameView game = new GameView(new ModelImpl(null));
    
    public GamePanel() {
        this.setSize(800,600);
        this.getContentPane().add(game);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(final String args[]) {
        GamePanel game = new GamePanel();
    }

}
