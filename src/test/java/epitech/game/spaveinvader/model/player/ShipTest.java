package epitech.game.spaveinvader.model.player;

import epitech.game.spaveinvader.controller.ShipMouse;
import epitech.game.spaveinvader.vue.player.ShipView;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipTest {

    private Ship ship;

    @BeforeEach
    public void setup() {
        this.ship = new Ship();
        ShipMouse.mouse = new Point2D(0, 0);
    }

    @Test
    public void moveUp() {
        this.ship.move(Direction.UP);

        assertEquals(400, this.ship.getX(), "The ship was moved on X asis");
        assertEquals(400 - this.ship.getVelocity(), this.ship.getY(), "The ship wasn't moved on Y asis");
    }

    @Test
    public void moveDown() {
        this.ship.move(Direction.DOWN);

        assertEquals(400, this.ship.getX(), "The ship was moved on X asis");
        assertEquals(400 + this.ship.getVelocity(), this.ship.getY(), "The ship wasn't moved on Y asis");
    }

    @Test
    public void moveLeft() {
        this.ship.move(Direction.LEFT);

        assertEquals(400 - this.ship.getVelocity(), this.ship.getX(), "The ship wasn't moved on X asis");
        assertEquals(400, this.ship.getY(), "The ship was moved on Y asis");
    }

    @Test
    public void moveRight() {
        this.ship.move(Direction.RIGHT);

        assertEquals(400 + this.ship.getVelocity(), this.ship.getX(), "The ship wasn't moved on X asis");
        assertEquals(400, this.ship.getY(), "The ship was moved on Y asis");
    }

    @Test
    public void setY_OK() {
        this.ship.setY(100);

        assertEquals(400, this.ship.getX(), "The x attribute was moved");
        assertEquals(100, this.ship.getY(), "The y attribute wasn't moved");
    }

    @Test
    public void setY_OverTheLimit() {
        this.ship.setY(GameLauncher.HEIGHT_SCREEN *2);

        assertEquals(400, this.ship.getX(), "The x attribute was moved");
        assertEquals(GameLauncher.HEIGHT_SCREEN - ShipView.SIZE_SHIP, this.ship.getY(), "The y attribute was moved over the limit !");
    }

    @Test
    public void setY_UnderTheLimit() {
        this.ship.setY(-1);

        assertEquals(400, this.ship.getX(), "The x attribute was moved");
        assertEquals(0, this.ship.getY(),  "The y attribute was moved under 0 !");
    }

    @Test
    public void setX_OK() {
        this.ship.setX(100);

        assertEquals(100, this.ship.getX(), "The x attribute wasn't moved");
        assertEquals(400, this.ship.getY(), "The y attribute was moved");
    }

    @Test
    public void setX_OverTheLimit() {
        this.ship.setX(GameLauncher.WIDTH_SCREEN * 2);

        assertEquals(GameLauncher.WIDTH_SCREEN - ShipView.SIZE_SHIP, this.ship.getX(),"The x attribute was moved over the limit");
        assertEquals(400, this.ship.getY(), "The y attribute was moved");
    }

    @Test
    public void setX_UnderTheLimit() {
        this.ship.setX(-1);

        assertEquals(0, this.ship.getX(), "The x attribute was moved under the limit");
        assertEquals(400, this.ship.getY(), "The y attribute was moved");
    }

    @Test
    public void changeAngleUp() {
        // Ship(400, 400) | Mouse(420, 200) 420 becauce is the center of your spaceship
        ShipMouse.mouse = new Point2D(420, 200);
        this.ship.computeAngle();
        assertEquals(360, this.ship.getAlpha(), "Calcul angle are bad");
    }

    @Test
    public void changeAngleDown() {
        ShipMouse.mouse = new Point2D(420, 600);
        this.ship.computeAngle();
        assertEquals(180, this.ship.getAlpha(), "Calcul angle are bad");
    }

    @Test
    public void changeAngleRigth() {
        ShipMouse.mouse = new Point2D(600, 400);
        this.ship.computeAngle();
        assertEquals(90, this.ship.getAlpha(), "Calcul angle are bad");
    }

    @Test
    public void changeAngleLeft() {
        ShipMouse.mouse = new Point2D(200, 400);
        this.ship.computeAngle();
        assertEquals(270, this.ship.getAlpha(), "Calcul angle are bad");
    }

    @Test
    public void calculAngleWhileMoving() {
        ShipMouse.mouse = new Point2D(420 + this.ship.getVelocity(),200);
        this.ship.move(Direction.RIGHT);
        assertEquals(360, this.ship.getAlpha(), "Calcul angle while moving are bad");
    }

    @Test
    public void launchProjectileTest_rigth() {
        this.ship.setAlpha(90);
        Projectile p = this.ship.launchProjectile();
        assertEquals(this.ship.getVelocity() *2,  p.getDx(), "The vector was too bad");
        assertEquals(0, p.getDy(), "The vector was too bad");
    }

    @Test
    public void launchProjectileTest_left() {
        this.ship.setAlpha(270);
        Projectile p = this.ship.launchProjectile();

        int valueDy = (int) Math.round(p.getDy());
        int valueDx = (int) Math.round(p.getDx());
        assertEquals(0, valueDy, "The vector was too bad");
        assertEquals(-2* this.ship.getVelocity() ,  valueDx, "The vector was too bad");
    }

    @Test
    public void launchProjectileTest_up() {
        this.ship.setAlpha(360);
        Projectile p = this.ship.launchProjectile();

        int valueDy = (int) Math.round(p.getDy());
        int valueDx = (int) Math.round(p.getDx());
        assertEquals(-2 * this.ship.getVelocity(), valueDy, "The vector was too bad");
        assertEquals(0, valueDx, "The vector was too bad");
    }

    @Test
    public void launchProjectileTest_down() {
        this.ship.setAlpha(180);
        Projectile p = this.ship.launchProjectile();

        int valueDy = (int) Math.round(p.getDy());
        int valueDx = (int) Math.round(p.getDx());
        assertEquals(this.ship.getVelocity() * 2, valueDy , "The vector was too bad");
        assertEquals(0, valueDx, "The vector was too bad");
    }

}
