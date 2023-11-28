package epitech.game.spaveinvader.model.enemy;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.player.Ship;
import epitech.game.spaveinvader.vue.enemy.MissileView;
import javafx.geometry.Point2D;

public class Missile extends Enemy {

    public Missile() {
        super();
        this.dx = Double.MAX_VALUE;
        this.dy = Double.MAX_VALUE;
    }
    public Missile(double x, double y){
        super(x,y);

    }


    @Override
    protected void makeVector(Ship ship) {
        Point2D pointPerso = new Point2D(x + MissileView.SIZE_MISSILE / 2.0, y);
        Point2D pointShip = new Point2D(ship.getX(), ship.getY());
        Point2D border = new Point2D(GameLauncher.WIDTH_SCREEN + 50, this.y);
        double alpha = pointPerso.angle(
                pointShip, // position de la souris
                border
        );

        double alphaRadians;
        if (this.y < ship.getY()) {
            alphaRadians = Math.toRadians(alpha);
            setAlpha(alpha+45);

        } else {
            alphaRadians = Math.toRadians(360 - alpha);
            setAlpha(360 - alpha+45);

        }

        this.dx = (velocity * Math.cos(alphaRadians));
        this.dy = (velocity * Math.sin(alphaRadians));
        // System.out.println("(" + this.x + ", " + this.y + ") : (" + this.dx + ", " + this.dy + ")");
    }


}
