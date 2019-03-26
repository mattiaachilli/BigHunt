package model.gun;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Model;
import model.ModelImpl;

class AmmoTest {

    private final Model model = new ModelImpl();

    @Test
    void test() {
        this.model.shoot();
        assertEquals(this.model.getCurrentMagazine(), 1);
        assertEquals(this.model.getBullets(), 4);
        for (int i = 0; i <= ModelImpl.MAX_MAGAZINES / 4; i++) {
            this.model.shoot();
        }
        assertEquals(this.model.getCurrentMagazine(), 2);
        assertEquals(this.model.getBullets(), 3);
        this.model.activateInfAmmo();
        for (int i = 0; i <= ModelImpl.MAX_MAGAZINES; i++) {
            this.model.shoot();
        }
        assertEquals(this.model.getCurrentMagazine(), 2);
        assertEquals(this.model.getBullets(), 3);
        this.model.deactivateInfAmmo();
        for (int i = 0; i <= ModelImpl.MAX_MAGAZINES / 4; i++) {
            this.model.shoot();
        }
        assertEquals(this.model.getCurrentMagazine(), 3);
        assertEquals(this.model.getBullets(), 2);
//        for (int i = 0; i < ModelImpl.MAX_MAGAZINES * 5; i++) {
//            this.model.shoot();
//        }
//        assertEquals(this.model.getCurrentMagazine(), ModelImpl.MAX_MAGAZINES);
//        assertEquals(this.model.getBullets(), 0);
    }

}
