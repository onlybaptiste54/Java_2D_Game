package epitech.game.spaveinvader.model.leaderboard;

import java.io.Serializable;

public class UserScore implements Serializable, Comparable<UserScore> {

    private String name;

    private int score;

    public UserScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(UserScore o) {
        return Integer.compare(this.getScore(), o.getScore()) * -1;
    }
}
