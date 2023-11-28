package epitech.game.spaveinvader.model.player;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.Entity;
import epitech.game.spaveinvader.model.observator.Observable;
import epitech.game.spaveinvader.model.observator.Observator;

import java.util.ArrayList;
import java.util.List;

public class Projectile extends Entity implements Observable {
    private final List<Observator> observatorList = new ArrayList<>();
    private final double dx;
    private final double dy;

    public Projectile(double x, double y , int velocity , double alpha) {
        super(x,y,1,velocity,alpha);
        alpha = Math.toRadians(alpha); // Convertir l'angle en radians

        this.dx =  (velocity * Math.cos(alpha));
        this.dy =   (velocity * Math.sin(alpha));
    }



    @Override
    public void addObservator(Observator observator) {
        if (observator == null) return;
        if (!observatorList.contains(observator)) {
            observatorList.add(observator);
            observator.notify(this);
        }
    }
    public void removeObservator(){
      observatorList.clear();
    }


    @Override
    public void notifyAllObservator() {
        for (Observator observator : observatorList) {
            //this c'est un observable, une instance d'entity qui implémente elle meme un observable.
            observator.notify(this);
        }
    }

    public boolean move(){

        this.x = this.x +this.dx;
        this.y = this.y + this.dy;
        notifyAllObservator();

        if(this.x<0 || this.x> GameLauncher.WIDTH_SCREEN){
            return false;
        }else return !(this.y < 0) && !(this.y > GameLauncher.HEIGHT_SCREEN);
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

}
