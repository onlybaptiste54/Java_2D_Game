package epitech.game.spaveinvader.model;

import epitech.game.spaveinvader.model.leaderboard.Leaderboard;
import epitech.game.spaveinvader.model.player.Projectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    @BeforeEach
    public void setup() {
        this.game = new Game(new Leaderboard());
    }

    @Test
    public void move_Projectiles_OneRemoved() {
        Projectile p1 = new Projectile(400, 400, 10, 0);
        Projectile p2 = new Projectile(0, 400, 10, 180);
        game.getProjectileList().add(p1);
        game.getProjectileList().add(p2);
        assertTrue(game.loop());
        assertEquals(1, game.getProjectileList().size(), "A projectile wasn't removed");
        assertEquals(410, p1.getX(), "The p1 projectile wasn't moved");
    }


    @Test
    public void move_Projectiles_NoOneRemoved() {
        Projectile p1 = new Projectile(400, 400, 10, 0);
        Projectile p2 = new Projectile(400, 400, 10, 180);
        Projectile p3 = new Projectile(400, 400, 10, 90);
        Projectile p4 = new Projectile(400, 400, 10, 270);
        game.getProjectileList().addAll(Arrays.asList(p1,p2,p3,p4));
        game.loop();
        assertEquals(410, p1.getX(), "The projectile p1 wasn't moved");
        assertEquals(390, p2.getX(), "The projectile p2 wasn't moved");
        assertEquals(410, p3.getY(), "The projectile p1 wasn't moved");
        assertEquals(390, p4.getY(), "The projectile p2 wasn't moved");
    }


}
