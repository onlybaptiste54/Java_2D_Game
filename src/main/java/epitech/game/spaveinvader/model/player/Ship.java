package epitech.game.spaveinvader.model.player;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.Entity;
import epitech.game.spaveinvader.vue.player.ShipView;
import javafx.geometry.Point2D;

import static epitech.game.spaveinvader.controller.ShipMouse.mouse;

public class Ship extends Entity implements ManagingProjectile{

    /**
     * Create an instance of ship
     * Default value:
     * x = 400
     * y = 400
     * life = 100
     * TypeProjectile = "missile"
     * velocity = 4
     * alpha = 0
     */
    public Ship(){
        super(400,400,3,8,0);
    }


    /**
     * Move the ship in the direction passed in parameters
     * @param all Direction to move ship
     */
    public void move(Direction all){
        switch (all){
            case UP:
                setY(getY()-getVelocity());//y -= velocity

                break;
            case DOWN:
                setY(getY()+getVelocity());
                break;
            case LEFT:
                setX(getX()-getVelocity());
                break;
            case RIGHT:
                setX(getX()+getVelocity());

                break;
        }
        computeAngle();

    }

    /**
     * Compute the angle would have the ship to fix the mouse cursor
     */
    public void computeAngle(){
        // position de notre joueur
        Point2D pointShip = new Point2D(x+ ShipView.SIZE_SHIP/2.0,y);
        double alphaShoot = pointShip.angle(
                mouse, // position de la souris
                new Point2D(GameLauncher.WIDTH_SCREEN,this.y) // position du bord droit
        );

        // Change the angle of ship +90 to put image look at the good position
        if(mouse.getY()<this.y){
            // Top of screen
            setAlpha(360-alphaShoot+90);
        }else {
            // Bottom of screen
            setAlpha(alphaShoot + 90);
        }
    }


    /**
     * Create a new projectile with there x, y, velocity and alpha
     * @return projectile
     */
    @Override
    public Projectile launchProjectile() {
        return new Projectile(this.getX(), this.getY(), this.getVelocity()*2, this.getAlpha()-90);
    }
}
