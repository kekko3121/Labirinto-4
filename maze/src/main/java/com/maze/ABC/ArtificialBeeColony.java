package com.maze.ABC;

import com.maze.Interactors.*;

import java.util.Random;

public class ArtificialBeeColony {
    private static final int NUM_BEES = 10; // Numero di "api"

    private final Random rand; // Generatore di numeri casuali
    private final Position exitMaze; // Posizione dell'uscita nel labirinto
    private final Box[][] maze; // Labirinto

    public ArtificialBeeColony(Box[][] maze, Position exitMaze) {
        this.maze = maze;
        this.exitMaze = exitMaze;
        this.rand = new Random();
    }

    public Position moveMicrorobot(Position currentPosition) {
        Position bestPosition = currentPosition;
        double bestQuality = calculateQuality(currentPosition);

        for (int i = 0; i < NUM_BEES; i++) {
            Position newPosition = generateRandomMove(currentPosition);
            double newQuality = calculateQuality(newPosition);
            if (newQuality > bestQuality) {
                bestQuality = newQuality;
                bestPosition = newPosition;
            }
        }

        return bestPosition;
    }

    private Position generateRandomMove(Position currentPosition) {
        int moveX = rand.nextInt(3) - 1;
        int moveY = rand.nextInt(3) - 1;

        int newX = currentPosition.getX() + moveX;
        int newY = currentPosition.getY() + moveY;

        if (isValidPosition(newX, newY)) {
            return new Position(newX, newY);
        } else {
            // If the new position is not valid, return the current position
            return currentPosition;
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < maze.length &&
                y >= 0 && y < maze[0].length &&
                maze[x][y].getValue() != ValueBox.WALL;
    }

    private double calculateQuality(Position position) {
        double distance = Math.sqrt(Math.pow(position.getX() - exitMaze.getX(), 2) +
                Math.pow(position.getY() - exitMaze.getY(), 2));
        return 1.0 / (1.0 + distance);
    }
}