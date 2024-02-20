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
 * Classe per gestire il gioco, crea un labirinto, aggiunge il microrobot e notifica gli iscritti alla "newsletter" del gioco.
 */
public class Game implements Observable {

    private Maze maze; // Oggetto classe labirinto

    private ArrayList<Microrobot> microrobots; // Il microrobot che si muove nel labirinto

    private ArrayList<PositionSubcriber> subscribers; // Lista degli iscritti alla "newsletter" del gioco

    /**
     * Costruttore per prendere in input la difficoltà del labirinto passata come enumerazione
     * In base alla difficoltà scelta, crea il labirinto richiamando il Factory Method
     * Aggiunge i microrobot al labirinto
     */
    public Game(Hardships h, int n) {
        this.maze = new MazeDifficulty().createMaze(h);
        this.microrobots = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        addMicrorobots(n);
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
     * notificando loro la posizione e lo stato dei microrobot.
     */
    public void notifySubscribers() {
        for (PositionSubcriber subscriber : subscribers) {
            for (Microrobot microrobot : microrobots) {
                subscriber.update(this.getMaze(), microrobot.getPosition(), microrobot.getMicroRobotState());
            }
        }
    }

    /**
     * Metodo per aggiungere i microrobot al labirinto
     * @param n
     */
    public void addMicrorobots(int n) {
        for (int i = 0; i < n; i++) {
            Position p = getRandomEmptyPosition();
            if (p != null) {
                Microrobot microrobot = new Microrobot(p, new OneMoveState(new OneMove(maze.getMaze(), maze.getExitMaze())));
                microrobots.add(microrobot);
            } else {
                break; // Esci dal loop
            }
        }
    }

    /**
     * Metodo per restituire una posizione vuota casuale nel labirinto
     * @return
     */
    private Position getRandomEmptyPosition() {
        int maxAttempts = maze.getDim() * maze.getDim(); // Numero massimo di tentativi per trovare una posizione vuota
        int attempts = 0;
    
        while (attempts < maxAttempts) {
            Position p = new Position(ThreadLocalRandom.current().nextInt(maze.getDim() - 1), ThreadLocalRandom.current().nextInt(maze.getDim() - 1));
            
            // Verifica se la posizione è vuota
            if (p.getX() >= 0 && p.getX() < maze.getDim() && p.getY() >= 0 && p.getY() < maze.getDim() &&
                maze.getMaze()[p.getX()][p.getY()].getValue() != ValueBox.WALL && !isMicrorobotAtPosition(p)) {
                return p; // Restituisci la posizione vuota valida
            }
            
            attempts++;
        }
        
        return null; // Nessuna posizione vuota valida trovata dopo il numero massimo di tentativi
    }
    

    /**
     * Metodo per verificare se c'è un microrobot in una posizione specifica
     * @param p
     * @return microrobot position
     */
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

    /**
     * Metodo per restituire la posizione dell'uscita del labirinto
     */

    public Position getExitMaze() {
        return this.maze.getExitMaze();
    }

    public void moveMicrorobots() {
        for (Microrobot microrobot : microrobots) {
            Position currentPosition = microrobot.getPosition();
            Position nextPosition = microrobot.getMicroRobotState().doAction(currentPosition);
    
            // Verifica se la prossima posizione è valida per il movimento del microrobot
            // Verifica se la posizione è all'interno del labirinto e non è un muro
            if (nextPosition.getX() >= 0 && nextPosition.getX() < maze.getDim() && nextPosition.getY() >= 0 && nextPosition.getY() < maze.getDim() &&
                maze.getMaze()[nextPosition.getX()][nextPosition.getY()].getValue() != ValueBox.WALL) {
                // Verifica se la posizione è occupata da un altro microrobot
                boolean isOccupied = false;
                for (Microrobot robot : microrobots) {
                    if (robot.getPosition().equals(nextPosition)) {
                        isOccupied = true; // La posizione è occupata da un altro microrobot
                        break;
                    }
                }
                if (!isOccupied) {
                    microrobot.move(); // Esegue il movimento del microrobot
                }
            }
        }
    }
}