package epitech.game.spaveinvader.model.collectible;

import epitech.game.spaveinvader.model.player.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectibleTest {

    Collectible collectible;
    Ship test;

    @BeforeEach
    public void setup() {
        collectible = new Collectible(400,400) {
            @Override
            public void doEffect() {

            }
        };
        test = new Ship();
    }

    @Test
    public void testCollide() {
        test.setX(400);
        test.setY(400);
        assertTrue(collectible.collide(test));

        test.setX(429);
        test.setY(400);
        assertTrue(collectible.collide(test));
    }

    @Test
    public void noCollide() {
        test.setX(0); test.setY(0);
        assertFalse(collectible.collide(test));
    }



}
