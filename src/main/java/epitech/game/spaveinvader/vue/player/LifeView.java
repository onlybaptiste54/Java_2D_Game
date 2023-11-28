package epitech.game.spaveinvader.vue.player;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.observator.Observable;
import epitech.game.spaveinvader.model.observator.Observator;
import epitech.game.spaveinvader.model.player.Ship;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LifeView extends HBox implements Observator {

    private static final ImageView LIFE_IMG = new ImageView(String.valueOf(GameLauncher.class.getResource("images/life.png")));
    private final Label nbLife;

    public LifeView() {
        nbLife = new Label("3");
        nbLife.setTextFill(Color.WHITE);
        nbLife.setFont(Font.font(25));
        LIFE_IMG.setFitHeight(25);
        LIFE_IMG.setFitWidth(25);
        setAlignment(Pos.BASELINE_CENTER);
        getChildren().addAll(nbLife, LIFE_IMG);
    }

    @Override
    public void notify(Observable observable) {
        if (observable instanceof Ship ship) {
            if (Platform.isFxApplicationThread()) {
                nbLife.setText(ship.getVie() + "");
            } else {
                Platform.runLater(() -> nbLife.setText(ship.getVie() + ""));
            }
        }
    }
}
