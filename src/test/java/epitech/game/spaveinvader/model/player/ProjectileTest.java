package epitech.game.spaveinvader.model.player;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest {


    @Test
    public void move_right() {
        Projectile projectile = new Projectile(400, 400, 10, 180);
        assertTrue( projectile.move());
        assertEquals(390, projectile.getX(), "The X position wasn't changed");
        assertEquals(400, projectile.getY(), "The Y position was changed");
    }

    @Test
    public void move_left() {
        Projectile projectile = new Projectile(400, 400, 10, 0);
        assertTrue( projectile.move());
        assertEquals(410, projectile.getX(), "The X position wasn't changed");
        assertEquals(400, projectile.getY(), "The Y position was changed");
    }

    @Test
    public void move_down() {
        Projectile projectile = new Projectile(400, 400, 10, 90);
        assertTrue( projectile.move());
        assertEquals(400, projectile.getX(), "The X position was changed");
        assertEquals(410, projectile.getY(), "The Y position wasn't changed");
    }

    @Test
    public void move_up() {
        Projectile projectile = new Projectile(400, 400, 10, 270);
        assertTrue( projectile.move());
        assertEquals(400, projectile.getX(), "The X position was changed");
        assertEquals(390, projectile.getY(), "The Y position wasn't changed");
    }

    @Test
    public void move_toTheLeftLimit() {
        Projectile projectile = new Projectile(0, 400, 10, 180);
        assertFalse(projectile.move(), "The projectile isn't outside the map");
        assertEquals(-10, projectile.getX(), "The X position wasn't changed");
        assertEquals(400, projectile.getY(), "The Y position was changed");
    }

    @Test
    public void move_toTheRigthLimit() {
        Projectile projectile = new Projectile(GameLauncher.WIDTH_SCREEN, 400, 10, 0);
        assertFalse(projectile.move(), "The projectile isn't outside the map");
        assertEquals(GameLauncher.WIDTH_SCREEN + 10,  projectile.getX(), "The X position wasn't changed");
        assertEquals(400, projectile.getY(), "The Y position was changed");
    }

    @Test
    public void move_toTheBottomLimit() {
        Projectile projectile = new Projectile(400, GameLauncher.HEIGHT_SCREEN, 10, 90);
        assertFalse(projectile.move(), "The projectile isn't outside the map");
        assertEquals(400, projectile.getX(), "The X position was changed");
        assertEquals(GameLauncher.HEIGHT_SCREEN + 10, projectile.getY(), "The Y position wasn't changed");

    }

    @Test
    public void move_toTheTopLimit() {
        Projectile projectile = new Projectile(400, 0, 10, 270);
        assertFalse(projectile.move(), "The projectile isn't outside the map");
        assertEquals(400, projectile.getX(), "The X position was changed");
        assertEquals(-10, projectile.getY(), "The Y position wasn't changed");
    }

    @Test
    public void projectile_moving() {
        // i = the angle of projectile (90 * i)
        for (int i = 0; i < 4; i++) {
            Projectile projectile = new Projectile(400, 400, 10, 90*i);
            for (int j = 0; j < 50; j++) {
                if (projectile.getX() <= 0 || projectile.getX() >= GameLauncher.WIDTH_SCREEN ||
                        projectile.getY() <= 0 || projectile.getY() >= GameLauncher.HEIGHT_SCREEN)
                    assertFalse(projectile.move(), "The projectile isn't outside of the map: " + i);
                else
                    assertTrue(projectile.move(), "The projectile is outside of the map: "+ i);
            }
        }
    }

}
