package epitech.game.spaveinvader.model.collectible;

import epitech.game.spaveinvader.model.player.Ship;

import java.util.Timer;
import java.util.TimerTask;

public class SpeedBoostBonus extends Collectible {

    private static boolean effectInAction = false;
    private Ship ship;

    public SpeedBoostBonus(double x, double y, Ship ship) {
        super(x, y);
        this.ship = ship;
    }

    @Override
    public void doEffect() {
        if (effectInAction) return;
        effectInAction = true;
        int baseVelocity = ship.getVelocity();
        ship.setVelocity((int) (baseVelocity * 1.5));
        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        ship.setVelocity(baseVelocity);
                        effectInAction = false;
                        timer.cancel();
                    }
                },
                10000
        );
    }
}
