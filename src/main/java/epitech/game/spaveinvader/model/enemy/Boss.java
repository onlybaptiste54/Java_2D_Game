package epitech.game.spaveinvader.model.enemy;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.player.ManagingProjectile;
import epitech.game.spaveinvader.model.player.Ship;
import epitech.game.spaveinvader.vue.enemy.BossView;
import javafx.geometry.Point2D;

public class Boss extends Enemy implements ManagingProjectile {

    private double defaultAngle;

    public Boss() {
        super();
        this.x = Math.min(GameLauncher.WIDTH_SCREEN - BossView.SIZE_BOSS, this.x);
        this.y = Math.min(GameLauncher.HEIGHT_SCREEN - BossView.SIZE_BOSS, this.y);
        this.vie = 10;
        this.dx = Double.MAX_VALUE;
        this.dy = Double.MAX_VALUE;
        this.defaultAngle = 90;
    }


    @Override
    public Missile launchProjectile() {
        return new Missile(this.getX(), this.getY());
    }

    @Override


    protected void makeVector(Ship ship) {
        Point2D pointPerso = new Point2D(x + BossView.SIZE_BOSS / 2.0, y);

        Point2D pointShip = new Point2D(ship.getX(), ship.getY());
        Point2D border = new Point2D(GameLauncher.WIDTH_SCREEN + 50, this.y);
        double alpha = pointPerso.angle(
                pointShip,
                border);

        if (ship.getY() > this.getY())
            setAlpha(alpha - defaultAngle);
        else
            setAlpha(360 - alpha - defaultAngle);

        if (this.dx == Double.MAX_VALUE || this.dy == Double.MAX_VALUE) {

            // Si l'axe de spawn est x
            if (this.x == GameLauncher.WIDTH_SCREEN - BossView.SIZE_BOSS || this.x == 0) {
                // Se déplace sur l'axe y
                this.dx = 0;
                this.dy = velocity;
                // Si l'axe de spawn est y
            } else {

                // Se deplace sur l'axe x
                this.dx = velocity;
                this.dy = 0;
            }
        }

        if (this.x < 0 || this.x > GameLauncher.WIDTH_SCREEN - BossView.SIZE_BOSS)
            this.dx *= -1;

        if (this.y < 0 || this.y > GameLauncher.HEIGHT_SCREEN - BossView.SIZE_BOSS)
            this.dy *= -1;

    }
}











