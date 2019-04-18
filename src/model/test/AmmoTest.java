package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.Model;
import model.ModelImpl;
import model.matches.GameMode;

class AmmoTest {

    private static final String ERROR_MESSAGE = "Error";

    private static final int MAX_BULLETS = 8;
    private static final int MAX_MAGAZINES = 20;
    private final Model model = new ModelImpl();

    @Test
    public void test() {
        this.model.initGame(GameMode.STORY_MODE); //Initializing story mode
        this.model.shoot(); //Shoots one time
        assertEquals(ERROR_MESSAGE, this.model.getCurrentMagazine().getNumber(), 1); //Should still be at the first magazine
        assertEquals(ERROR_MESSAGE, this.model.getBullets(), MAX_BULLETS - 1); //Should have seven bullets
        for (int i = 0; i <=  MAX_MAGAZINES / 2; i++) {
            if (!this.model.canShoot()) {
                this.model.recharge(); //Simulating a recharge when needed
            }
            this.model.shoot();
        }
        assertEquals(ERROR_MESSAGE, this.model.getCurrentMagazine().getNumber(), 2); //Should be using the second magazine
        assertEquals(ERROR_MESSAGE, this.model.getBullets(), MAX_BULLETS - 4); //With five bullets remaining
        for (int i = 0; i <= MAX_MAGAZINES / 2; i++) {
            this.model.shoot(); //Testing without recharging
        }
        assertEquals(ERROR_MESSAGE, this.model.getCurrentMagazine().getNumber(), 2); //Should still be using the second magazine but with no ammo left
        assertEquals(ERROR_MESSAGE, this.model.getBullets(), 0);
        for (int i = 0; i <= MAX_MAGAZINES; i++) {
            this.model.recharge(); //Using every  magazine
        }
        assertTrue(ERROR_MESSAGE, this.model.isGameOver()); //Should be game over for story mode rules
    }

}
