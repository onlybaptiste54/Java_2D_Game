package epitech.game.spaveinvader.vue.collectible;

import epitech.game.spaveinvader.GameLauncher;
import javafx.scene.image.Image;

public class ScoreBonusView extends CollectibleView {

    private static final Image image = new Image(String.valueOf(GameLauncher.class.getResource("images/scoreBonus.png")));

    public ScoreBonusView() {
        super(image);
    }
}
