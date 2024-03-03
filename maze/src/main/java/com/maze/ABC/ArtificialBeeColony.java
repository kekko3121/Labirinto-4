package com.maze.ABC;

import com.maze.Interactors.*;

public class ArtificialBeeColony {
    private final Position exitMaze;
    private final Box[][] maze;

    public ArtificialBeeColony(Box[][] maze, Position exitMaze) {
        this.maze = maze;
        this.exitMaze = exitMaze;
    }

    public Position moveMicrorobot(Position currentPosition) {
        // Inizializzazione
        Position bestPosition = currentPosition;
        double bestQuality = Double.NEGATIVE_INFINITY;
    
        // Generazione delle posizioni adiacenti e selezione della migliore
        for (int moveX = -1; moveX <= 1; moveX++) {
            for (int moveY = -1; moveY <= 1; moveY++) {
                // Ignora la posizione corrente
                if (moveX == 0 && moveY == 0) continue;
    
                int newX = currentPosition.getX() + moveX;
                int newY = currentPosition.getY() + moveY;
    
                // Controlla se la posizione è valida
                if (isValidMove(newX, newY)) {
                    Position newPosition = new Position(newX, newY);
                    double newQuality = calculateQuality(newPosition);
                    if (newQuality > bestQuality) {
                        bestQuality = newQuality;
                        bestPosition = newPosition;
                    }
                }
            }
        }
    
        return bestPosition;
    }
    
    private boolean isValidMove(int x, int y) {
        // Controlla se la posizione è all'interno del labirinto e non è una parete
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y].getValue() != ValueBox.WALL;
    }
    
    private double calculateQuality(Position position) {
        double distance = Math.sqrt(Math.pow(position.getX() - exitMaze.getX(), 2) + Math.pow(position.getY() - exitMaze.getY(), 2));
        return 1.0 / (1.0 + distance);
    }    
}