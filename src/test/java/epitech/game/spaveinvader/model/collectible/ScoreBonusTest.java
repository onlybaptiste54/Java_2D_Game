package epitech.game.spaveinvader.model.collectible;

import epitech.game.spaveinvader.model.Game;
import epitech.game.spaveinvader.model.leaderboard.Leaderboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBonusTest {

    ScoreBonus bonus;

    Game game;

    @BeforeEach
    public void setup() {
        game = new Game(new Leaderboard());
        bonus = new ScoreBonus(10, 10, game);
    }

    @Test
    public void doEffectMethod() {

        assertEquals(0, game.getScore(), "The score isn't 0");
        bonus.doEffect();
        assertEquals(100, game.getScore(), "The score doesn't increase");


    }
}
