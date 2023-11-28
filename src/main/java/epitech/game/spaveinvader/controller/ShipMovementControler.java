package epitech.game.spaveinvader.controller;

import epitech.game.spaveinvader.model.player.Direction;
import epitech.game.spaveinvader.model.player.Ship;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Detect every keyboard key
 */
public class ShipMovementControler implements EventHandler<KeyEvent> {


    /**
     * Ship will update data
     */
    private final Ship ship;

    /**
     * Every keys was pressed on keyboard
     */
    private final Set<Direction> directions = new HashSet<>();

    /**
     * Create a new event handle can handle all key event
     * @param ship ship data
     */
    public ShipMovementControler(Ship ship) {
        this.ship = ship;
    }

    /**
     * doActions is a method get every key on the list
     * and do action if it's possible (only for moveable key)
     */
    public void doAction(){

        for (Direction dir : directions) {
            this.ship.move(dir);
        }
    }

    /**
     * These handler detect if key was pressed or released
     * It removes the key if was released and
     * add the key if was pressed
     * @param keyEvent Information of key
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        Direction dir;
        switch (keyEvent.getCode()) {
            case UP, Z:
                dir = Direction.UP;
                break;
            case DOWN, S:
                dir = Direction.DOWN;
                break;
            case RIGHT, D:
                dir = Direction.RIGHT;
                break;
            case LEFT, Q:
                dir = Direction.LEFT;
                break;
            default:
                return;
        }
        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED))
            this.directions.add(dir);
        else
            this.directions.remove(dir);
    }

}
