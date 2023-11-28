package epitech.game.spaveinvader.model.collectible;

import epitech.game.spaveinvader.model.Entity;
import epitech.game.spaveinvader.model.player.Ship;
import epitech.game.spaveinvader.model.observator.Observable;
import epitech.game.spaveinvader.model.observator.Observator;

import java.util.ArrayList;
import java.util.List;

public abstract class Collectible extends Entity {



    public Collectible(double x, double y) {
        super(x, y, 0, 0, 0);
    }

    public abstract void doEffect();

    public boolean collide(Ship ship) {
        return ship.getX() + 30 > this.getX() && ship.getX() < this.getX() + 30 &&
                ship.getY() + 30 > this.getY() && ship.getY() < this.getY() + 30;
    }

}
