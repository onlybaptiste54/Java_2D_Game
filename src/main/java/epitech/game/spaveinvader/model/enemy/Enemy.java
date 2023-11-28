package epitech.game.spaveinvader.model.enemy;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.Entity;
import epitech.game.spaveinvader.model.player.Ship;

import java.util.Random;

public abstract class Enemy extends Entity {

    public static int velocity = 5;

    protected double dx;
    protected double dy;


    public Enemy() {
        super(400, 400, 1, velocity, 0);
        axes();
    }

    public Enemy(double x, double y) {
        super(x, y, 1, 5, 0);
    }


    protected abstract void makeVector(Ship ship);

    public boolean move(Ship ship) {
        this.makeVector(ship);
        this.x = this.x + this.dx;
        this.y = this.y + this.dy;
        notifyAllObservator();

        if (this.x < 0 || this.x > GameLauncher.WIDTH_SCREEN) {
            return false;
        } else return !(this.y < 0) && !(this.y > GameLauncher.HEIGHT_SCREEN);
    }


    public boolean axes() {
        Random rand = new Random();
        int randomChoice = rand.nextInt(2);

        if (randomChoice == 0) {
            this.x = generateRandomX();
            this.y = new double[]{0, GameLauncher.HEIGHT_SCREEN}[rand.nextInt(2)];
        } else {
            this.x = new double[]{0, GameLauncher.WIDTH_SCREEN}[rand.nextInt(2)];

            this.y = generateRandomY();
        }
        return true;
    }

    private static int generateRandomY() {
        Random rand = new Random();
        // Ajustez ces valeurs selon la plage y souhaitée
        return rand.nextInt(GameLauncher.HEIGHT_SCREEN) + 50;
    }

    private static int generateRandomX() {
        Random rand = new Random();
        // Ajustez ces valeurs selon la plage y souhaitée
        return rand.nextInt(GameLauncher.WIDTH_SCREEN) + 50;
    }

    public boolean collide(Entity entity) {
        if (entity.getX() + 30 > this.getX() && entity.getX() < this.getX() + 30 &&
                entity.getY() + 30 > this.getY() && entity.getY() < this.getY() + 30) {
            this.vie -= 1;
            return true; // Il y a eu une collision
        } else {
            return false; // Pas de collision
        }
    }


}
