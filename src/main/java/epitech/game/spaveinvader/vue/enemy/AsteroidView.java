package epitech.game.spaveinvader.vue.enemy;

import epitech.game.spaveinvader.GameLauncher;
import javafx.scene.image.Image;

public class AsteroidView extends EnemyView {
    public static final int SIZE_ASTEROID= 40;
    private static final Image image = new Image(String.valueOf(GameLauncher.class.getResource("images/asteroid.png")));

    public AsteroidView(){
        super(image);
        setFitHeight(SIZE_ASTEROID);
        setFitWidth(SIZE_ASTEROID);

    }





}
