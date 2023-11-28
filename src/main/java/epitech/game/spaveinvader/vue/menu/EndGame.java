package epitech.game.spaveinvader.vue.menu;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.Game;
import epitech.game.spaveinvader.model.leaderboard.Leaderboard;
import epitech.game.spaveinvader.model.leaderboard.UserScore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Set;

public class EndGame extends BorderPane {

    private Leaderboard leaderboard;
    private Game game;

    public EndGame(Game game) {
        this.game = game;
        this.leaderboard = game.getLeaderboard();
        setPrefSize(700, 800);
        setTop(title(game.getScore()));
        setCenter(leaderBoard());
        setBottom(menu());
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private Node title(int score) {
        Label title = new Label("Score : " + score);
        title.setFont(Font.font("OldStyle", 40));
        title.setTextFill(Color.WHITE);
        VBox box = new VBox(title);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private Node leaderBoard() {
        VBox board = new VBox();
        if (this.leaderboard.isEmpty()) {
            Label label = new Label("No score was registered !");
            board.getChildren().add(label);
        } else {
            for(int i=0; i<10; i++) {
                UserScore user = this.leaderboard.get(i);
                if (user == null) continue;
                Label name = new Label( (i+1) + ". " +user.getName());
                name.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                name.setTextFill(Color.WHITE);
                Label score = new Label(""+ user.getScore());
                score.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                score.setTextFill(Color.WHITE);
                HBox line = new HBox(name, score);
                line.setAlignment(Pos.CENTER);
                line.setSpacing(10);
                board.getChildren().add(line);
            }
        }
        board.setAlignment(Pos.CENTER);
        return board;
    }

    private Node menu() {

        VBox box = new VBox(registerLeaderboard(), commandButtons());
        box.setPadding(new Insets(60));
        box.setSpacing(20);
        return box;
    }

    private Node commandButtons() {
        Button retry = new Button("Retry");
        retry.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        retry.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        retry.setTextFill(Color.BLACK);
        retry.setOnAction(event -> GameLauncher.game());

        Button quit = new Button("Quit");
        quit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        quit.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        quit.setTextFill(Color.BLACK);
        quit.setOnAction(ActionEvent -> GameLauncher.close());
        HBox lineCommand = new HBox(retry, quit);
        lineCommand.setAlignment(Pos.CENTER);
        lineCommand.setSpacing(20);
        return lineCommand;
    }

    private Node registerLeaderboard() {
        TextField textField = new TextField();
        textField.setFocusTraversable(false);
        Button register = new Button("Register Score");
        register.setOnAction(actionEvent -> leaderboard.addScore(
                textField.getText(),
                game.getScore()
        ));

        HBox line = new HBox(textField, register);
        line.setAlignment(Pos.CENTER);
        line.setSpacing(5);
        return line;
    }
}
