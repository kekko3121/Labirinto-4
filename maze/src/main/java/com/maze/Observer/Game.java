import java.util.HashSet;
import java.util.Set;

public class Game implements Observable {
    private Set<PositionSubscriber> subscribers;
    private Box[][] maze;
    private Set<Microrobot> microrobots;

    public Game() {
        subscribers = new HashSet<>();
        microrobots = new HashSet<>();
        // Inizializzazione del labirinto e dei microrobot
    }

    @Override
    public void subscribe(PositionSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (PositionSubscriber subscriber : subscribers) {
            subscriber.update(maze, microrobots);
        }
    }

    // Altri metodi per gestire il gioco, come aggiungere microrobot, muovere microrobot, ecc.

    public void addMicrorobot(Microrobot microrobot) {
        microrobots.add(microrobot);
    }

    public void updateMicrorobotPosition(Microrobot microrobot, Position newPosition) {
        microrobot.setPosition(newPosition);
    }

    public void setMaze(Box[][] maze) {
        this.maze = maze;
    }

    // Altri metodi per gestire il gioco
}
