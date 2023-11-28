package epitech.game.spaveinvader.model.enemy;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.player.Ship;
import epitech.game.spaveinvader.vue.enemy.AsteroidView;
import javafx.geometry.Point2D;

public class Asteroid extends Enemy {
    public Asteroid() {
        super();
        this.dx = Double.MAX_VALUE;
        this.dy = Double.MAX_VALUE;
    }


    @Override
    protected void makeVector(Ship ship) {
        if (this.dx == Double.MAX_VALUE || this.dy == Double.MAX_VALUE) {

            Point2D pointPerso = new Point2D(x + AsteroidView.SIZE_ASTEROID / 2.0, y);

            Point2D pointShip = new Point2D(ship.getX(), ship.getY());
            Point2D border = new Point2D(GameLauncher.WIDTH_SCREEN + 50, this.y);
            double alpha = pointPerso.angle(
                    pointShip,
                    border);
            setAlpha(0);


            double alphaRadians;
            if (this.y < ship.getY()) {
                alphaRadians = Math.toRadians(alpha);
            } else {
                alphaRadians = Math.toRadians(360 - alpha);
            }

            this.dx = (velocity * Math.cos(alphaRadians));
            this.dy = (velocity * Math.sin(alphaRadians));
            //System.out.println("(" + this.x + ", " + this.y + ") : (" + this.dx + ", " + this.dy + ")");
        }
    }


}
