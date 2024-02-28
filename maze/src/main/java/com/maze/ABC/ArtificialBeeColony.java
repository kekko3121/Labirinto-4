package com.maze.ABC;

import com.maze.Interactors.*;

import java.util.Random;

public class ArtificialBeeColony {
    private static final int NUM_BEES = 10;
    private static final int MAX_TRIES = 3; // Numero massimo di tentativi per una mossa casuale
    private static final double EXPLORE_PROBABILITY = 0.3; // Probabilità di esplorazione
    private static final double ABANDON_THRESHOLD = 0.1; // Soglia per abbandonare una soluzione

    private final Random rand;
    private final Position exitMaze;
    private final Box[][] maze;

    public ArtificialBeeColony(Box[][] maze, Position exitMaze) {
        this.maze = maze;
        this.exitMaze = exitMaze;
        this.rand = new Random();
    }

    public Position moveMicrorobot(Position currentPosition) {
        Position bestPosition = currentPosition;
        double bestQuality = calculateQuality(currentPosition);
        int tries = 0;

        while (tries < MAX_TRIES) {
            Position newPosition = generateRandomMove(currentPosition);

            double newQuality = calculateQuality(newPosition);

            if (newQuality > bestQuality) {
                bestQuality = newQuality;
                bestPosition = newPosition;
            }

            tries++;
        }

        if (rand.nextDouble() < EXPLORE_PROBABILITY) {
            Position explorePosition = explore(currentPosition);
            double exploreQuality = calculateQuality(explorePosition);

            if (exploreQuality > bestQuality) {
                bestQuality = exploreQuality;
                bestPosition = explorePosition;
            }
        }

        if (bestQuality < ABANDON_THRESHOLD) {
            return currentPosition; // Abbandona la soluzione e rimane fermo
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
            return currentPosition;
        }
    }

    private Position explore(Position currentPosition) {
        int moveX = rand.nextInt(3) - 1;
        int moveY = rand.nextInt(3) - 1;

        int newX = currentPosition.getX() + moveX;
        int newY = currentPosition.getY() + moveY;

        return new Position(newX, newY);
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y].getValue() != ValueBox.WALL;
    }

    private double calculateQuality(Position position) {
        double distance = Math.sqrt(Math.pow(position.getX() - exitMaze.getX(), 2) + Math.pow(position.getY() - exitMaze.getY(), 2));
        return 1.0 / (1.0 + distance);
    }
}