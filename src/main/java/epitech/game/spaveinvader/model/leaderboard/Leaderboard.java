package epitech.game.spaveinvader.model.leaderboard;

import epitech.game.spaveinvader.GameLauncher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard implements Serializable {

    public static final String FILE_PATH = "src/main/resources/epitech/game/spaveinvader/leaderboard";

    private final List<UserScore> order;

    public Leaderboard() {
        this.order = new ArrayList<>();
    }

    public void addScore(String name, int score) {
        int i = 0;
        boolean find = false;
        while (i < this.order.size() && !find) {
            UserScore user = this.order.get(i);
            if (user.getName().equals(name)) {
                user.setScore(score);
                find = true;
            }
            i++;
        }

        if (!find) {
            this.order.add(new UserScore(name, score));
        }
        this.sort();
    }

    public UserScore get(int i) {
        if (i < this.order.size())
            return this.order.get(i);
        else return null;
    }

    private void sort() {
        this.order.sort(UserScore::compareTo);
    }

    public boolean isEmpty() {
        return this.order.isEmpty();
    }

}
