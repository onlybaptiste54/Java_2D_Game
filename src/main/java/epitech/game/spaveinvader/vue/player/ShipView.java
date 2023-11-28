package epitech.game.spaveinvader.vue.player;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.Entity;
import epitech.game.spaveinvader.model.observator.Observable;
import epitech.game.spaveinvader.model.observator.Observator;
import epitech.game.spaveinvader.vue.EntityView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShipView extends EntityView implements Observator {
    public static final int SIZE_SHIP= 40;

    private static final Image image = new Image(String.valueOf(GameLauncher.class.getResource("images/ship.png")));

    public ShipView(){
        super(image);
        setFitHeight(SIZE_SHIP);
        setFitWidth(SIZE_SHIP);

    }


}
