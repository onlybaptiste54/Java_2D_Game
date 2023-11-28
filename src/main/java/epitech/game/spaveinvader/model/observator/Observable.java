package epitech.game.spaveinvader.model.observator;

public interface Observable {

    void addObservator(Observator observator);
    void notifyAllObservator();

}
