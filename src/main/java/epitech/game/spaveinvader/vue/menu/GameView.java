package epitech.game.spaveinvader.vue.menu;

import epitech.game.spaveinvader.GameLauncher;
import epitech.game.spaveinvader.model.Game;
import epitech.game.spaveinvader.model.collectible.Collectible;
import epitech.game.spaveinvader.model.collectible.ScoreBonus;
import epitech.game.spaveinvader.model.collectible.SpeedBoostBonus;
import epitech.game.spaveinvader.model.collectible.UnlimitedProjectileBonus;
import epitech.game.spaveinvader.model.enemy.Asteroid;
import epitech.game.spaveinvader.model.enemy.Boss;
import epitech.game.spaveinvader.model.enemy.Enemy;
import epitech.game.spaveinvader.model.enemy.Missile;
import epitech.game.spaveinvader.model.observator.Observable;
import epitech.game.spaveinvader.model.observator.Observator;
import epitech.game.spaveinvader.model.player.Projectile;
import epitech.game.spaveinvader.vue.collectible.CollectibleView;
import epitech.game.spaveinvader.vue.collectible.ScoreBonusView;
import epitech.game.spaveinvader.vue.collectible.SpeedBoostBonusView;
import epitech.game.spaveinvader.vue.collectible.UnlimitedProjectileBonusView;
import epitech.game.spaveinvader.vue.enemy.AsteroidView;
import epitech.game.spaveinvader.vue.enemy.BossView;
import epitech.game.spaveinvader.vue.enemy.EnemyView;
import epitech.game.spaveinvader.vue.enemy.MissileView;
import epitech.game.spaveinvader.vue.player.LifeView;
import epitech.game.spaveinvader.vue.player.ProjectileView;
import epitech.game.spaveinvader.vue.player.ShipView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class GameView extends Pane implements Observator {
    private final ShipView shipView;

    private final LifeView lifeView;
    private final List<BossView> bossViews;
    private final List<ProjectileView> projectileList;
    private final List<EnemyView> enemyViews;


    private final List<CollectibleView> collectibleViews;

    private static final Image background = new Image(String.valueOf(GameLauncher.class.getResource("images/background_game.jpg")));
    public GameView(Game game) {
        setMinSize(GameLauncher.WIDTH_SCREEN,GameLauncher.HEIGHT_SCREEN);
        setBackground(new Background(new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1, 1, true, true, false, false)
        )));
        this.projectileList= new ArrayList<>();
        this.collectibleViews = new ArrayList<>();
        this.enemyViews = new ArrayList<>();
        this.bossViews = new ArrayList<>();

        this.shipView=new ShipView();
        this.lifeView = new LifeView();
        game.getShip().addObservator(shipView);
        game.getShip().addObservator(lifeView);
        init(game);

    }

    private void clear() {
        getChildren().clear();
        projectileList.clear();
        collectibleViews.clear();
        enemyViews.clear();
        bossViews.clear();
    }

    private void add() {
        getChildren().addAll(collectibleViews);
        getChildren().addAll(enemyViews);
        getChildren().addAll(projectileList);
        getChildren().add(shipView);
        getChildren().addAll(bossViews);
        getChildren().add(lifeView);
    }

    private void init(Game game){
        clear();
        for (Boss b : game.getBoss()) {
            b.removeObservator();
            BossView view = new BossView();
            b.addObservator(view);
            this.bossViews.add(view);
        }

        for(Projectile projectile: game.getProjectileList()){
            projectile.removeObservator();
            ProjectileView projectileView = new ProjectileView();
            projectile.addObservator(projectileView);
            projectileList.add(projectileView);


        }

        for(Enemy enemy: game.getEnemies()) {
            enemy.removeObservator();

            EnemyView view = null;
            if (enemy instanceof Asteroid)
                view = new AsteroidView();
            else if (enemy instanceof Missile)
                view = new MissileView();
            enemy.addObservator(view);
            enemyViews.add(view);
        }


        for(Collectible collectible : game.getCollectibles()) {
            collectible.removeObservator();
            CollectibleView collectibleView = null;
            if (collectible instanceof ScoreBonus)
                collectibleView = new ScoreBonusView();
            else if (collectible instanceof UnlimitedProjectileBonus)
                collectibleView = new UnlimitedProjectileBonusView();
            else if (collectible instanceof SpeedBoostBonus)
                collectibleView = new SpeedBoostBonusView();
            collectible.addObservator(collectibleView);
            collectibleViews.add(collectibleView);
        }


        // get children obitent des nodes .
        add();

    }


    @Override
    public void notify(Observable observable) {
        if(observable instanceof Game game){
            init(game);

        }
    }
}
