package epitech.game.spaveinvader.vue.collectible;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.vue.EntityView;
import javafx.scene.image.Image;

public abstract class CollectibleView extends EntityView {

    protected CollectibleView(Image image) {
        super(image);
        setFitHeight(30);
        setFitWidth(30);
    }
}
