package model.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import model.Model;
import model.ModelImpl;

class AmmoTest {

    private static final int MAX_MAGAZINES = 20;
    private final Model model = new ModelImpl();

    @Test
    void test() {
        this.model.shoot();
        assertEquals(this.model.getCurrentMagazine().getNumber(), 1);
        assertEquals(this.model.getBullets(), 4);
        for (int i = 0; i <=  MAX_MAGAZINES / 4; i++) {
            this.model.shoot();
        }
        assertEquals(this.model.getCurrentMagazine().getNumber(), 2);
        assertEquals(this.model.getBullets(), 3);
//        this.model.activateInfAmmo();
//        for (int i = 0; i <= ModelImpl.MAX_MAGAZINES; i++) {
//            this.model.shoot();
//        }
//        assertEquals(this.model.getCurrentMagazine().getNumber(), 2);
//        assertEquals(this.model.getBullets(), 3);
//        this.model.deactivateInfAmmo();
        for (int i = 0; i <= MAX_MAGAZINES / 4; i++) {
            this.model.shoot();
        }
        assertEquals(this.model.getCurrentMagazine().getNumber(), 3);
        assertEquals(this.model.getBullets(), 2);
//        for (int i = 0; i < ModelImpl.MAX_MAGAZINES * 5; i++) {
//            this.model.shoot();
//        }
//        assertEquals(this.model.getCurrentMagazine(), ModelImpl.MAX_MAGAZINES);
//        assertEquals(this.model.getBullets(), 0);
    }

}
