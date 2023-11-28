package epitech.game.spaveinvader.model.collectible;

import epitech.game.spaveinvader.controller.ShipMouse;

import java.util.Timer;
import java.util.TimerTask;

public class UnlimitedProjectileBonus extends Collectible {

    private static boolean effectInAction = false;
    public UnlimitedProjectileBonus(double x, double y) {
        super(x, y);
    }

    @Override
    public void doEffect() {
        if(effectInAction) return;
        long cooldown = ShipMouse.COOLDOWN_TIME;
        ShipMouse.COOLDOWN_TIME = 0;
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ShipMouse.COOLDOWN_TIME = cooldown;
                effectInAction = false;
                timer.cancel();
            }
        };
        timer.schedule(timerTask, 10000);
        effectInAction = true;
    }
}
