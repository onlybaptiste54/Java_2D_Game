package epitech.game.spaveinvader.vue;

import epitech.game.spaveinvader.model.Entity;
import epitech.game.spaveinvader.model.observator.Observable;
import epitech.game.spaveinvader.model.observator.Observator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class EntityView extends ImageView implements Observator {

    public EntityView(Image image) {
        super(image);
    }

    @Override
    public void notify(Observable observable) {
        if (observable instanceof Entity){
            Entity myobservable = (Entity) observable;
            // Ici on veut juste observer les valeurs du modéles, Le x de ma view = x m de mon modéle;
            this.setX(myobservable.getX());
            this.setY(myobservable.getY());
            this.setRotate(myobservable.getAlpha());
        }

    }
}
