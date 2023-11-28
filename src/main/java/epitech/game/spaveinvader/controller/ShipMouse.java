package epitech.game.spaveinvader.controller;

import epitech.game.spaveinvader.model.Game;
import epitech.game.spaveinvader.model.player.Projectile;
import epitech.game.spaveinvader.model.player.Ship;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;


/**
 * Event while mouse move
 * Change the data in ship
 */
public class ShipMouse implements EventHandler<MouseEvent> {

    /**
     * COOLDOWN_TIME is the cooldown between each projectile
     */
    public static long COOLDOWN_TIME = 500;

    /**
     * Position of mouse the last time was moved
     */
    public static Point2D mouse = new Point2D(0, 0);
    /**
     * The last time where the launchProjectile was invoke
     */
    private long lastExecutionTime = 0;


    /**
     * Game data to change
     */
    private final Game game;

    /**
     * Ship data to change
     */
    private final Ship ship;


    /**
     * Create a event handler of mouseEvent does some action
     * on data game
     * @param game data of game
     */
    public ShipMouse(Game game) {
        this.ship = game.getShip();
        this.game = game;
    }

    /**
     * Handle a mouseEvent
     * @param mouseEvent mouse moved or clicked
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        mouse = new Point2D(x, y);

        // Compute the new angle after change the mouse position
        ship.computeAngle();



        // If mouse wasn't clicked
        if (mouseEvent.getEventType() != MouseEvent.MOUSE_CLICKED) return;

        // get currentTime in program
        long currentTime = System.currentTimeMillis();

        // If cooldown wasn't finished
        if (currentTime - lastExecutionTime < COOLDOWN_TIME) {
            System.out.println("Cooldown is active");
            return;
        }

        // change the last execution time
        lastExecutionTime = currentTime;

        // Get a new projectile of ship
        Projectile projectile = ship.launchProjectile();

        // Add these new projectile in game list
        game.getProjectileList().add(projectile);
        // Notify these game view
        Platform.runLater(game::notifyAllObservator);



    }


}





