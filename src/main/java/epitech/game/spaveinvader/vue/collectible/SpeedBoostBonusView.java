package epitech.game.spaveinvader.vue.collectible;

import epitech.game.spaveinvader.GameLauncher;
import javafx.scene.image.Image;

public class SpeedBoostBonusView extends CollectibleView {
    private static final Image image = new Image(String.valueOf(GameLauncher.class.getResource("images/speedBoost.png")));

    public SpeedBoostBonusView() {
        super(image);
    }
}
