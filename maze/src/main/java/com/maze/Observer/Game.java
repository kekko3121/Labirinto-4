package com.maze.Observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.maze.Strategy.Microrobot;
import com.maze.Strategy.OneMove;

import javafx.geometry.Pos;

import com.maze.Factory.Maze;
import com.maze.Factory.MazeDifficulty;
import com.maze.Interactors.Box;
import com.maze.Interactors.Hardships;
import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;

/**
 * Classe per creare e gestire il gioco
 * @see Observable
 */
public class Game implements Observable {

    private Maze maze; // labirinto
    
    private List<Microrobot> microrobots; // microrobots - i giocatori all'interno del labirinto

    private List<MicrorobotPosition> observers; // Array list per aggiornare tutte le classi appartenenti alla newsletter

    private GameStateMemento previousState; // Memento per salvare lo stato del gioco

    private Set<Position> occupiedPositions; // Posizioni occupate dai microrobots

    /**
     * Costruttore per inizializzare il labirinto con la difficolta e il numero microrobot scelto dall'utente
     * @param hardships
     */
    public Game(Hardships hardships) {
        maze = new MazeDifficulty().createMaze(hardships); // crea il labirinto con la difficoltà scelta
        microrobots = new ArrayList<>(); // inizializza la lista dei microrobot
        observers = new ArrayList<>(); // inizializza la lista degli osservatori
        occupiedPositions = new HashSet<>();

        // Assicurarsi che il labirinto sia stato creato
        assert maze != null;
    }

    /**
     * Metodo per iscrivere un osservatore alla newsletter
     * @param observer
     */
    public void subscribe(MicrorobotPosition observer) {
        observers.add(observer);
    }

    /**
     * Metodo per notificare tutti gli osservatori iscritti alla newsletter
     */
    public void notifyObservers() {
        for (MicrorobotPosition observer : observers) {
            observer.update(microrobots, maze.getMaze());
        }
    }

    public Box[][] getMaze() {
        return maze.getMaze();
    }

    /*
     * Metodo per restituire la posizione di un microrobot
     */
    public Position getMicrorobotPosition(int i){
        return microrobots.get(i).getPosition();
    }

    /**
     * Metodo per aggiungere un microrobot all'interno del labirinto
     */
    public void addMicrorobot() {
        Position newPosition;
        do {
            newPosition = new Position(ThreadLocalRandom.current().nextInt(maze.getDim()), ThreadLocalRandom.current().nextInt(maze.getDim()));
        } while (!isValidPosition(newPosition) || maze.getBox(newPosition.getX(), newPosition.getY()).getValue() == ValueBox.WALL || occupiedPositions.contains(newPosition));
        
        // Aggiungi la nuova posizione al set delle posizioni occupate
        occupiedPositions.add(newPosition);
    
        microrobots.add(new Microrobot(newPosition, new OneMove(maze.getMaze(), maze.getExitMaze())));
    }

    public void removeMicrorobot(int i) {
        occupiedPositions.remove(microrobots.get(i).getPosition());
        microrobots.remove(i);
    }

    /**
     * Metodo per verificare se la posizione è valida
     * @param position
     * @return
     */
    private boolean isValidPosition(Position position) {

        // Verifica se la posizione è al di fuori del labirinto
        if (position.getX() <= 0 || position.getX() >= maze.getDim() || position.getY() <= 0 || position.getY() >= maze.getDim()) {
            return false; // La posizione è al di fuori del labirinto
        }

        // Verifica se la posizione è una botola o un muro
        if (maze.getBox(position.getX(), position.getY()).getValue() != ValueBox.EMPTY) {
            return false; // La casella non è vuota
        }

        // Verifica se la posizione è già occupata da un altro microrobot
        for (Microrobot microrobot : microrobots) {
            if (microrobot.getPosition().equals(position)) {
                return false; // La posizione è già occupata da un altro microrobot
            }
        }

        return true;
    }

    /**
     * Metodo per muovere il microrobot all'interno del labirinto
     */
    public void moveMicrorobot() {
        for (Microrobot microrobot : microrobots) { // Per ogni microrobot
            occupiedPositions.remove(microrobot.getPosition()); // Rimuovi la posizione occupata dal microrobot
            microrobot.move(); // Muove il microrobot

            if (maze.getBox(microrobot.getPosition().getX(), microrobot.getPosition().getY()).getValue() == ValueBox.HATCH) {
                Position newPosition;

                do {
                    newPosition = new Position(ThreadLocalRandom.current().nextInt(maze.getDim()), ThreadLocalRandom.current().nextInt(maze.getDim()));
                } while (!isValidPosition(newPosition) || maze.getBox(newPosition.getX(), newPosition.getY()).getValue() == ValueBox.WALL || occupiedPositions.contains(newPosition));

                microrobot.setActualPosition(newPosition);
            }
            occupiedPositions.add(microrobot.getPosition()); // Aggiungi la nuova posizione occupata dal microrobot
        }
    }
    

    public Position getExitPosition() {
        return maze.getExitMaze();
    }

    /**
     * Metodo per salvare lo stato attuale del gioco
     */
    public void saveState() {
        previousState = new GameStateMemento(new ArrayList<>(microrobots), copyMaze());
    }

    /** 
     * Metodo per ripristinare lo stato precedente del gioco
    */
    public void restoreState() {
        if (previousState != null) {
            microrobots.clear();
            microrobots.addAll(previousState.getMicrorobots());
            copyMazeData(previousState.getMaze(), maze.getMaze());
        }
    }

    /**
     * Metodo per copiare il labirinto
     * @return
     */
    private Box[][] copyMaze() {
        Box[][] newMaze = new Box[maze.getMaze().length][maze.getMaze()[0].length];
        copyMazeData(maze.getMaze(), newMaze);
        return newMaze;
    }

    /**
     * Metodo per copiare il labirinto
     * @param source
     * @param destination
     */
    private void copyMazeData(Box[][] source, Box[][] destination) {
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, source[i].length);
        }
    }

    /**
     * Classe per salvare lo stato del gioco
     */
    public class GameStateMemento {
        private final List<Microrobot> microrobots; // salvare lo stato dei microrobots
        private final Box[][] maze; // salvare lo stato del labirinto

        /**
         * Costuttore per passare lo stato del gioco
         * @param microrobots
         * @param maze
         */
        public GameStateMemento(List<Microrobot> microrobots, Box[][] maze) {
            this.microrobots = microrobots;
            this.maze = maze;
        }

        /**
         * Metodo per restituire lo stato dei microrobots
         * @return
         */
        public List<Microrobot> getMicrorobots() {
            return microrobots;
        }

        /**
         * Metodo per restituire lo stato del labirinto
         * @return
         */
        public Box[][] getMaze() {
            return maze;
        }
    }
}