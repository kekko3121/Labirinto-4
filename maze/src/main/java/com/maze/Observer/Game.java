package com.maze.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.maze.Strategy.Microrobot;
import com.maze.Strategy.OneMove;
import com.maze.Factory.Maze;
import com.maze.Factory.MazeDifficulty;
import com.maze.Interactors.Box;
import com.maze.Interactors.Hardships;
import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;

public class Game implements Observable {
    private Maze maze; // labirinto
    
    private List<Microrobot> microrobots; // microrobots - i giocatori all'interno del labirinto

    private List<MicrorobotPosition> observers; // Array list per aggiornare tutte le classi appartenenti alla newsletter

    public Game(Hardships hardships, int n) {
        maze = new MazeDifficulty().createMaze(hardships);
        microrobots = new ArrayList<>();
        observers = new ArrayList<>();

        // Assicurarsi che il labirinto sia stato creato
        assert maze != null;
    }

    public void subscribe(MicrorobotPosition observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (MicrorobotPosition observer : observers) {
            observer.update(microrobots, maze.getMaze());
        }
    }

    public Box[][] getMaze() {
        return maze.getMaze();
    }

    public Position getMicrorobotPosition(int i){
        return microrobots.get(i).getPosition();
    }

    public void addMicrorobot() {
        Position newPosition;

        do {
            newPosition = new Position(ThreadLocalRandom.current().nextInt(maze.getDim()), ThreadLocalRandom.current().nextInt(maze.getDim()));
        } while (!isValidPosition(newPosition));
        microrobots.add(new Microrobot(newPosition, new OneMove(maze.getMaze(), maze.getExitMaze())));
    }

    private boolean isValidPosition(Position position) {

        if (position.getX() < 0 || position.getX() >= maze.getDim() || position.getY() < 0 || position.getY() >= maze.getDim()) {
            return false; // La posizione è al di fuori del labirinto
        }

        if (maze.getBox(position.getX(), position.getY()).getValue() != ValueBox.EMPTY) {
            return false; // La casella non è vuota
        }

        for (Microrobot microrobot : microrobots) {
            if (microrobot.getPosition().equals(position)) {
                return false; // La posizione è già occupata da un altro microrobot
            }
        }

        return true;
    }

    public void moveMicrorobot() {
        for (Microrobot microrobot : microrobots) {
            Position currentPosition = microrobot.getPosition();
            Position newPosition = microrobot.getMicroRobotStrategy().nextMove(currentPosition);
    
            if (isValidPosition(newPosition)) {
                microrobot.move();
    
                // Verifica se la nuova posizione è una botola
                if (maze.getBox(newPosition.getX(), newPosition.getY()).getValue() == ValueBox.HATCH) {
                    // Genera casualmente una nuova posizione fino a trovare una casella vuota
                    do {
                        newPosition = new Position(ThreadLocalRandom.current().nextInt(maze.getDim()), ThreadLocalRandom.current().nextInt(maze.getDim()));
                    } while (!isValidPosition(newPosition));
                    
                    // Muovi il microrobot nella nuova posizione
                    microrobot.setActualPosition(newPosition);
                }
            }
        }
    }
}
