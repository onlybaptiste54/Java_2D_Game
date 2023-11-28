package epitech.game.spaveinvader.vue.enemy;

import epitech.game.spaveinvader.GameLauncher;
import javafx.scene.image.Image;

public class MissileView extends EnemyView {
    public static final int SIZE_MISSILE= 40;
    private static final Image image = new Image(String.valueOf(GameLauncher.class.getResource("images/missile.png")));

    public MissileView(){
        super(image);
        setFitHeight(SIZE_MISSILE);
        setFitWidth(SIZE_MISSILE);


    }





}
