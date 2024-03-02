package com.maze.ABC;

import com.maze.Interactors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtificialBeeColony {
    private static final int MAX_TRIES = 3; // Numero massimo di tentativi per una mossa casuale
    private static final int NUM_BEES = 10; // Numero di microrobot (api)
    private static final double EXPLORE_PROBABILITY = 0.1; // Probabilità di esplorazione
    
    private final Random rand;
    private final Position exitMaze;
    private final Box[][] maze;

    public ArtificialBeeColony(Box[][] maze, Position exitMaze) {
        this.maze = maze;
        this.exitMaze = exitMaze;
        this.rand = new Random();
    }

    public Position moveMicrorobot(Position currentPosition) {
        List<Position> positions = generateInitialPositions(currentPosition);

        for (int i = 0; i < MAX_TRIES; i++) {
            positions = explorePositions(positions);
            positions = selectBestPositions(positions);
        }

        return getBestPosition(positions);
    }

    private List<Position> generateInitialPositions(Position currentPosition) {
        List<Position> positions = new ArrayList<>();
        positions.add(currentPosition);
        for (int i = 1; i < NUM_BEES; i++) {
            positions.add(generateRandomPosition());
        }
        return positions;
    }

    private Position generateRandomPosition() {
        int x = rand.nextInt(maze.length);
        int y = rand.nextInt(maze[0].length);
        return new Position(x, y);
    }

    private List<Position> explorePositions(List<Position> positions) {
        List<Position> newPositions = new ArrayList<>();
        for (Position position : positions) {
            if (rand.nextDouble() < EXPLORE_PROBABILITY) {
                newPositions.add(explore(position));
            } else {
                newPositions.add(position);
            }
        }
        return newPositions;
    }

    private Position explore(Position position) {
        int moveX = rand.nextInt(3) - 1;
        int moveY = rand.nextInt(3) - 1;
        int newX = position.getX() + moveX;
        int newY = position.getY() + moveY;

        // Verifica se la nuova posizione è valida (non è un muro)
        if (isValidPosition(newX, newY)) {
            return new Position(newX, newY);
        } else {
            return position; // Rimane nella posizione attuale se la nuova posizione è un muro
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y].getValue() != ValueBox.WALL;
    }

    private List<Position> selectBestPositions(List<Position> positions) {
        List<Position> bestPositions = new ArrayList<>();
        double bestQuality = Double.MIN_VALUE;

        for (Position position : positions) {
            double quality = calculateQuality(position);
            if (quality > bestQuality) {
                bestQuality = quality;
                bestPositions.clear();
                bestPositions.add(position);
            } else if (quality == bestQuality) {
                bestPositions.add(position);
            }
        }

        return bestPositions;
    }

    private Position getBestPosition(List<Position> positions) {
        if (positions.isEmpty()) {
            return null;
        } else {
            return positions.get(rand.nextInt(positions.size()));
        }
    }

    private double calculateQuality(Position position) {
        double distance = Math.sqrt(Math.pow(position.getX() - exitMaze.getX(), 2) + Math.pow(position.getY() - exitMaze.getY(), 2));
        return 1.0 / (1.0 + distance);
    }
}