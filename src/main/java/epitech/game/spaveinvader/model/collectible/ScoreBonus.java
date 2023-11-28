package epitech.game.spaveinvader.model.collectible;

import epitech.game.spaveinvader.model.Game;

public class ScoreBonus extends Collectible{

    private Game game;
    public ScoreBonus(double x, double y, Game game) {
        super(x, y);
        this.game = game;
    }

    @Override
    public void doEffect() {
        game.setScore(game.getScore() + 100);
    }
}
