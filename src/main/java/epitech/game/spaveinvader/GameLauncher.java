package epitech.game.spaveinvader;

import epitech.game.spaveinvader.controller.ShipMouse;
import epitech.game.spaveinvader.controller.ShipMovementControler;
import epitech.game.spaveinvader.model.Game;
import epitech.game.spaveinvader.model.leaderboard.Leaderboard;
import epitech.game.spaveinvader.vue.menu.EndGame;
import epitech.game.spaveinvader.vue.menu.GameView;
import epitech.game.spaveinvader.vue.menu.StartGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameLauncher extends Application {
    public static final BackgroundImage bg_menu = new BackgroundImage(
            new Image(String.valueOf(GameLauncher.class.getResource("images/background_menu.jpg"))),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
    );
    public static int WIDTH_SCREEN = 900;
    public static int HEIGHT_SCREEN = 800;

    private static Timer timer;
    public static Stage stage;

    private static Game game;

    private static Leaderboard leaderboard;

    public static void game() {
        game = new Game(leaderboard);
        GameView gameView = new GameView(game);
        game.addObservator(gameView);
        ShipMovementControler shipMovementControler = new ShipMovementControler(game.getShip());
        ShipMouse shipMouse = new ShipMouse(game);
        Scene scene = new Scene(gameView);
        scene.setOnKeyPressed(shipMovementControler);
        scene.setOnKeyReleased(shipMovementControler);


        scene.setOnMouseMoved(shipMouse);
        scene.setOnMouseClicked(shipMouse);
        // Le stage c'est ma fenetre en elle meme, et ma scene c'est le visuel.
        // Ca permet au controlleur de savoir d'ou il obtient les events. Le ship view ne sait pas a qui donner ces events. En lui donnant un controlleur on lui dit tient on doit lui donner a lui .
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                synchronized (this) {
                    if(game.loop()) Platform.runLater(game::notifyAllObservator);

                    shipMovementControler.doAction();
                    // The task you want to do
                    game.getShip().notifyAllObservator();
                    if(game.getShip().getVie()<=0){
                        Platform.runLater(GameLauncher::end);
                        timer.cancel();
                    }
                }

            }
        };


        timer.schedule(task, 0, 40);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            GameLauncher.saveLeaderboard();
            timer.cancel();
        });

        stage.setFullScreen(true);
    }


    public static void start() {
        stage.setOnCloseRequest(windowEvent -> GameLauncher.saveLeaderboard());
        stage.setScene(new Scene(new StartGame()));
        stage.setFullScreen(true);

    }

    public static void end() {
        stage.setOnCloseRequest(windowEvent -> GameLauncher.saveLeaderboard());
        stage.setScene(new Scene(new EndGame(game)));
        stage.setFullScreen(true);
    }

    @Override
    public void start(Stage stage) {
        loadLeaderboard();
        GameLauncher.stage = stage;
        start();


        stage.widthProperty().addListener((observableValue, number, t1) -> {
            GameLauncher.WIDTH_SCREEN = t1.intValue() - 16;

        });
        stage.heightProperty().addListener(((observableValue, number, t1) -> {
            GameLauncher.HEIGHT_SCREEN = t1.intValue() - 39;
        }));

        stage.fullScreenProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1) return;
            GameLauncher.HEIGHT_SCREEN = 900;
            GameLauncher.WIDTH_SCREEN = 800;
            stage.setWidth(800);
            stage.setHeight(900);
        });
        stage.setTitle("Space invader");
        stage.show();

    }

    private static void saveLeaderboard() {
        try {
            FileOutputStream fout = new FileOutputStream(Leaderboard.FILE_PATH);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(leaderboard);
            out.flush();
            out.close();
            System.out.println("Registered");
        } catch (Exception e) {
            System.out.println("Failed registered: " + e);
        }
    }

    private static void loadLeaderboard() {
        try {
            FileInputStream fin = new FileInputStream(Leaderboard.FILE_PATH);
            ObjectInputStream in = new ObjectInputStream(fin);
            leaderboard = (Leaderboard) in.readObject();
            System.out.println("Read successes");
        } catch (Exception e) {
            System.out.println("Failed read: " + e);
            leaderboard = new Leaderboard();
        }
    }
    public static void close() {
        saveLeaderboard();
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}
