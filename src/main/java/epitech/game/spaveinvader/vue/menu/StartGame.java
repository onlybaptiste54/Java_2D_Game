package epitech.game.spaveinvader.vue.menu;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartGame extends BorderPane {
    public StartGame() {
        setPrefSize(700, 800);
        setTop(title());
        setCenter(menu());
        setBackground(new Background(GameLauncher.bg_menu));
    }

    private Node title() {
        Label title = new Label("Space Invader");
        title.setFont(Font.font("OldStyle", 40));
        title.setTextFill(Color.WHITE);
        HBox box = new HBox(title);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private Node menu() {
        Button startButton = new Button("Start Game");
        startButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        startButton.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        startButton.setTextFill(Color.BLACK);
        startButton.setOnAction(ActionEvent -> GameLauncher.game());

        Button quit = new Button("Quit");
        quit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        quit.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        quit.setTextFill(Color.BLACK);
        quit.setOnAction(ActionEvent -> GameLauncher.close());

        VBox box = new VBox(startButton, quit);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(60));
        box.setSpacing(20);
        return box;
    }
}
