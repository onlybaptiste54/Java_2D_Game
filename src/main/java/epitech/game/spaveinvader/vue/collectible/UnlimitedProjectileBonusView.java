package epitech.game.spaveinvader.vue.collectible;

import epitech.game.spaveinvader.GameLauncher;
import javafx.scene.image.Image;

public class UnlimitedProjectileBonusView extends CollectibleView {

    private static final Image image = new Image(String.valueOf(GameLauncher.class.getResource("images/unlimitedProjectile.png")));

    public UnlimitedProjectileBonusView() {
        super(image);
    }
}
