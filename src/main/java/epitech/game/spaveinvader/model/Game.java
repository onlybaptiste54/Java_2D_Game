package epitech.game.spaveinvader.model;

import epitech.game.spaveinvader.model.collectible.Collectible;
import epitech.game.spaveinvader.model.collectible.ScoreBonus;
import epitech.game.spaveinvader.model.collectible.SpeedBoostBonus;
import epitech.game.spaveinvader.model.collectible.UnlimitedProjectileBonus;
import epitech.game.spaveinvader.model.enemy.Asteroid;
import epitech.game.spaveinvader.model.enemy.Boss;
import epitech.game.spaveinvader.model.enemy.Enemy;
import epitech.game.spaveinvader.model.enemy.Missile;
import epitech.game.spaveinvader.model.leaderboard.Leaderboard;
import epitech.game.spaveinvader.model.observator.Observable;
import epitech.game.spaveinvader.model.observator.Observator;
import epitech.game.spaveinvader.model.player.Projectile;
import epitech.game.spaveinvader.model.player.Ship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game implements Observable {
    private final List<Projectile> projectileList;

    private final List<Enemy> enemies;

    private final List<Observator> observatorList;

    private final List<Collectible> collectibles;


    private final Leaderboard leaderboard;

    private final Ship ship;
    private final List<Boss> boss;
    private int score;
    private int spawnCooldown = 1000;

    private long lastTime = 0;

    private int phase;

    private int multiplicator = 0;


    public Ship getShip() {
        return ship;
    }


    public Game(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
        this.score = 0;
        this.phase = 0;
        this.ship = new Ship();
        this.observatorList = new ArrayList<>();
        this.projectileList = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.collectibles = new ArrayList<>();
        this.boss = new ArrayList<>();
    }

    public boolean loop() {
        boolean change = false;
        score += 1;

        if (System.currentTimeMillis() - lastTime >= spawnCooldown) {
            switch (this.phase) {
                case 0:
                    Asteroid asteroid = new Asteroid();
                    enemies.add(asteroid);
                    break;
                case 1:
                    Missile missile = new Missile();
                    enemies.add(missile);
                    break;
                case 2:
                    boss.forEach(b -> this.enemies.add(b.launchProjectile()));
                    break;
            }
            lastTime = System.currentTimeMillis();
            change = true;
        }
        if (!boss.isEmpty())
            boss.forEach(b -> b.move(ship));

        Iterator<Projectile> iterator = this.getProjectileList().iterator();

        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();

            if (!projectile.move()) {
                iterator.remove();
                change = true;
                continue;
            }

            Iterator<Boss> bossIterator = this.boss.iterator();

            while (bossIterator.hasNext()) {
                Boss b = bossIterator.next();
                if (b.collide(projectile)) {
                    b.setVie(b.getVie() - 1);

                    if (b.getVie() <= 0) {
                        bossIterator.remove();

                        if (boss.isEmpty()) {
                            multiplicator++;
                            speedGame();
                            phase = 0;
                        }
                    }
                    iterator.remove();
                    change = true;
                }
            }
        }

        // Do all actions of enemies
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemyColision = enemyIterator.next();


            // Remove if asteroid move outside map
            if (!enemyColision.move(ship)) {
                enemyIterator.remove();
                change = true;
                continue;
            }

            // Remove iff asteroid collide a ship
            if (enemyColision.collide(ship)) {
                ship.setVie(ship.getVie() - 1);
                enemyIterator.remove();
                change = true;
                continue;
            }

            // Remove if asteroid collide a projectile
            Iterator<Projectile> projectileIterator = projectileList.iterator();
            boolean wasRemove = false;
            while (projectileIterator.hasNext() && !wasRemove) {
                Projectile projectileCollision = projectileIterator.next();

                if (enemyColision.collide(projectileCollision)) {
                    Collectible collectible = this.generateCollectible(enemyColision.getX(), enemyColision.getY());

                    enemyIterator.remove();
                    projectileIterator.remove();
                    change = true;
                    wasRemove = true;

                    if (collectible == null) continue;
                    this.collectibles.add(collectible);

                }

            }
        }

        Iterator<Collectible> collectibleIterator = this.collectibles.iterator();
        while (collectibleIterator.hasNext()) {
            Collectible collectible = collectibleIterator.next();
            if (collectible.collide(ship)) {
                collectible.doEffect();
                collectibleIterator.remove();
                change = true;
            }
        }

        if (phase == 0 && score > 1000 + 1000 * 3 * multiplicator) {
            phase = 1;

        } else if (phase == 1 && score > 2500 + 2500 * 3 * multiplicator) {
            for (int i = 0; i < Math.min(2, multiplicator + 1); i++)
                boss.add(new Boss());
            phase = 2;
        }


        return change;
    }


    private void speedGame() {
        Enemy.velocity++;
        spawnCooldown = Math.max(500, spawnCooldown - 100);
        ship.setVelocity(ship.getVelocity() + 1);
    }

    private Collectible generateCollectible(double x, double y) {
        Collectible collectible = null;

        int randomValue = (int) (Math.random() * 100);


        // 50%
        int probaScoreBonus = 33;

        // 5%
        int probaUnlimitedProjectileBonus = probaScoreBonus + 5;

        // 2%
        int probaSpeedBonus = probaUnlimitedProjectileBonus + 2;

        if (randomValue >= 0 && randomValue <= probaScoreBonus) {
            collectible = new ScoreBonus(x, y, this);
        } else if (randomValue > probaScoreBonus && randomValue <= probaUnlimitedProjectileBonus) {
            collectible = new UnlimitedProjectileBonus(x, y);
        } else if (randomValue > probaUnlimitedProjectileBonus && randomValue <= probaSpeedBonus) {
            collectible = new SpeedBoostBonus(x, y, this.getShip());
        }

        return collectible;
    }

    // alors j'ai 10 carte, toutes les cartes sont vides sauf une, a chaque fois que je vais faire une loop je vais tirer une carte, si cette carte est vide je ne fais rien, si la carte a un aasteroid en visuel j'en creer un .
    //EN terme de proba pur et dur si je fais un random sur 10, 1 chance sur 10 que le projectile spawn.
    // l'evolution c'est genre on passe sur une chance sur 5 .


    public synchronized List<Projectile> getProjectileList() {
        return projectileList;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public void addObservator(Observator observator) {
        if (!observatorList.contains(observator)) {
            observatorList.add(observator);

        }
    }

    @Override
    public void notifyAllObservator() {
        for (Observator observator : observatorList) {
            observator.notify(this);
        }

    }

    public List<Collectible> getCollectibles() {
        return this.collectibles;
    }

    public int getScore() {
        return score;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public List<Boss> getBoss() {
        return boss;
    }

    public void setScore(int score) {
        // Score cannot degrease
        this.score = Math.max(this.score, score);
    }
}
