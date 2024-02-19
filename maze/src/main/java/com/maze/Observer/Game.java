package com.maze.Observer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.maze.FactoryPattern.Maze;
import com.maze.FactoryPattern.MazeDifficulty;
import com.maze.Interactors.Hardships;
import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;
import com.maze.State.Microrobot;
import com.maze.State.OneMoveState;
import com.maze.Strategy.OneMove;
import com.maze.Interactors.Box;

/**
 * Classe per gestire il gioco, crea un labirinto,
 */
public class Game implements Observable {

    private Maze maze; // Oggetto classe labirinto

    private ArrayList<Microrobot> microrobots; // Il microrobot che si muove nel labirinto

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
                subscriber.update(this.getMaze(), microrobot.getPosition(), microrobot.getMicroRobotState());
            }
        }
    }
    public void addMicrorobots(int n) {
        for (int i = 0; i < n; i++) {
            Position p = getRandomEmptyPosition();
            if (p != null) {
                Microrobot microrobot = new Microrobot(p, new OneMoveState(new OneMove(maze.getMaze(), maze.getExitMaze())));
                microrobots.add(microrobot);
            } else {
                // Gestire il caso in cui non ci siano posizioni vuote disponibili
                System.out.println("Non ci sono posizioni vuote disponibili per spawnare il microrobot.");
                break; // Esci dal loop
            }
        }
    }

    private Position getRandomEmptyPosition() {
        int maxAttempts = maze.getDim() * maze.getDim(); // Numero massimo di tentativi
        int attempts = 0;
        while (attempts < maxAttempts) {
            Position p = new Position(ThreadLocalRandom.current().nextInt(maze.getDim() - 1), ThreadLocalRandom.current().nextInt(maze.getDim() - 1));
            // Verifica se la posizione è all'interno del labirinto e non è un muro e non è occupata da un altro microrobot
            if (p.getX() >= 0 && p.getX() < maze.getDim() && p.getY() >= 0 && p.getY() < maze.getDim() && maze.getMaze()[p.getX()][p.getY()].getValue() != ValueBox.WALL && !isMicrorobotAtPosition(p)) {
                return p; // Restituisci la posizione vuota valida
            }
            attempts++;
        }
        return null; // Nessuna posizione vuota valida trovata dopo il numero massimo di tentativi
    }


    private boolean isMicrorobotAtPosition(Position p) {
        // Verifica se c'è un microrobot nella posizione specificata
        for (Microrobot robot : microrobots) {
            if (robot.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo per restituire la posizione del microrobot
     * @param n
     * @return microrobot position
     */
    public Position getMicrorobotPosition(int n){
        return this.microrobots.get(n).getPosition();
    }

    /**
     * Metodo per restituire il labirinto
     */
    public Box[][] getMaze() {
        return this.maze.getMaze();
    }
}