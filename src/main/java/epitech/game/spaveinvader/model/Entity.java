package epitech.game.spaveinvader.model;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.observator.Observable;
import epitech.game.spaveinvader.model.observator.Observator;
import epitech.game.spaveinvader.vue.player.ShipView;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements Observable {
    private final List<Observator> observatorList = new ArrayList<>();
    protected double x;
    protected double y;
    protected int vie;

    @Override
    public void addObservator(Observator observator) {
        if (!observatorList.contains(observator)) {
            observatorList.add(observator);
            observator.notify(this);
        }
    }

    @Override
    public void notifyAllObservator() {
        for (Observator observator : observatorList) {
            //this c'est un observable, une instance d'entity qui implémente elle meme un observable.
            observator.notify(this);
        }
    }

    protected double alpha;
    protected int velocity;

    public Entity(double x, double y, int vie, int velocity, double alpha) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.alpha = alpha;
        this.vie = vie;

    }



    public double getX() {
        return x;
    }



    public void setX(double x) {
        //faire une méthode dans entity abstract getSIZE qui retourne la bonne SIZE.Chaque item aura sa propre size
        this.x = x;
        if (this.x > GameLauncher.WIDTH_SCREEN - ShipView.SIZE_SHIP) {

            this.x = GameLauncher.WIDTH_SCREEN - ShipView.SIZE_SHIP;
        } else if (this.x < 0 ) {
            this.x= 0;

        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {

        this.y = y;
        if (this.y > GameLauncher.HEIGHT_SCREEN - ShipView.SIZE_SHIP) {

            this.y = GameLauncher.HEIGHT_SCREEN - ShipView.SIZE_SHIP;
        } else if (this.y<0) {
            this.y=0;
        }
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = Math.max(0, vie);
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = Math.max(1, velocity);
    }

    public void removeObservator(){
        observatorList.clear();
    }

}
