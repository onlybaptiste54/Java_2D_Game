package epitech.game.spaveinvader.vue.player;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.player.Projectile;
import epitech.game.spaveinvader.model.observator.Observable;
import epitech.game.spaveinvader.model.observator.Observator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProjectileView extends ImageView implements Observator {
    public static final int SIZE_PROJECTILE= 40;


    private static final Image image = new Image(String.valueOf(GameLauncher.class.getResource("images/laser.png")));
    public ProjectileView() {
        super(image);
        setFitHeight(SIZE_PROJECTILE);
        setFitWidth(SIZE_PROJECTILE);

    }

    @Override
    public void notify(Observable observable) {
        if(observable instanceof Projectile){
            Projectile myobservable = (Projectile) observable;
            this.setX(myobservable.getX());
            this.setY(myobservable.getY());
            this.setRotate(myobservable.getAlpha()+90);

        }
    }


}
