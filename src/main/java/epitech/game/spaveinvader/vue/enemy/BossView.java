package epitech.game.spaveinvader.vue.enemy;

import epitech.game.spaveinvader.GameLauncher;
import javafx.scene.image.Image;

public class BossView extends EnemyView {
    public static final int SIZE_BOSS= 60;
    private static final Image image = new Image(String.valueOf(GameLauncher.class.getResource("images/boss.png")));

    public BossView(){
        super(image);
        setFitHeight(SIZE_BOSS);
        setFitWidth(SIZE_BOSS);

    }

}
