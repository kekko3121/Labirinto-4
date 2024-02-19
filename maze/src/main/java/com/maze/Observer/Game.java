package com.maze.Observer;

import com.maze.FactoryPattern.Maze;
import com.maze.FactoryPattern.MazeDifficulty;
import com.maze.Interactors.Hardships;
import com.maze.Interactors.Position;
import com.maze.State.Microrobot;
import com.maze.State.OneMoveState;
import com.maze.Strategy.OneMove;
import com.maze.Interactors.Box;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe per gestire il gioco,
 * crea un labirinto,
 */
public class Game implements Observable {

    private Maze maze; // Oggetto classe labirinto

    private ArrayList<Microrobot> microrobots; // Elenco dei microrobot nel labirinto

    private ArrayList<PositionSubcriber> subscribers; // Lista degli iscritti alla "newsletter" del gioco

    /**
     * Costruttore per prendere in input la difficoltà del labirinto passata come enumerazione
     * In base alla difficoltà scelta, crea il labirinto richiamando il Factory Method
     */
    public Game(Hardships h) {
        this.maze = new MazeDifficulty().createMaze(h);
        this.microrobots = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    @Override
    /**
     * Metodo per aggiungere un iscritto alla "newsletter" del gioco
     * @param subscriber
     */
    public void subscribe(PositionSubcriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    /**
     * Metodo per aggiornare tutti gli iscritti alla "newsletter" del gioco,
     * notificando loro la posizione e lo stato del microrobot, cambio di stato del labirinto e delle celle.
     */
    public void notifySubscribers() {
        for (PositionSubcriber subscriber : subscribers) {
            for (Microrobot microrobot : microrobots) {
                subscriber.update(this.maze.getMaze(), microrobot.getPosition(), microrobot.getMicroRobotState());
            }
        }
    }

    /**
     * Metodo per aggiungere microrobot al gioco in base al numero passato come parametro
     * @param n Numero di microrobot da aggiungere
     */
    public void addMicrorobots(int n) {
        for (int i = 0; i < n; i++) {
            Position position = generateRandomPosition();
            Microrobot microrobot = new Microrobot(position, new OneMoveState(new OneMove(maze.getMaze(), maze.getExitMaze())));
            microrobots.add(microrobot);
        }
    }

    /**
     * Metodo per generare una posizione casuale all'interno del labirinto.
     * @return Una posizione casuale valida all'interno del labirinto.
     */
    private Position generateRandomPosition() {
        int x = ThreadLocalRandom.current().nextInt(maze.getDim());
        int y = ThreadLocalRandom.current().nextInt(maze.getDim());
        return new Position(x, y);
    }

    /**
     * Metodo per muovere i microrobot nel labirinto e notificare gli iscritti.
     */
    public void moveMicrorobots() {
        for (Microrobot microrobot : microrobots) {
            microrobot.move();
        }
        notifySubscribers();
    }

    /**
     * Restituisce il labirinto del gioco.
     * @return Il labirinto del gioco.
     */
    public Box[][] getMaze() {
        return maze.getMaze();
    }
}
