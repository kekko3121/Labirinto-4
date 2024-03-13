package com.maze.ABC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.maze.Interactors.*;

public class ArtificialBeeColony {
    private final Position exitMaze;
    private final Box[][] maze;

    public ArtificialBeeColony(Box[][] maze, Position exitMaze) {
        this.maze = maze;
        this.exitMaze = exitMaze;
    }

    public Position moveMicrorobot(Position currentPosition) {
        return explore(currentPosition);
    }
        
    

    private Position explore(Position currentPosition) {
        Set<Position> visitedPositions = new HashSet<>();
        visitedPositions.add(currentPosition);

        List<Position> candidatePositions = new ArrayList<>();
        for (int moveX = -1; moveX <= 1; moveX++) {
            for (int moveY = -1; moveY <= 1; moveY++) {
                if (moveX == 0 && moveY == 0) continue;

                int newX = currentPosition.getX() + moveX;
                int newY = currentPosition.getY() + moveY;

                if (isValidMove(newX, newY) && !visitedPositions.contains(new Position(newX, newY))) {
                    candidatePositions.add(new Position(newX, newY));
                }
            }
        }

        return selectBestPosition(candidatePositions);
    }

    private Position selectBestPosition(List<Position> candidatePositions) {
        Position bestPosition = null;
        double bestQuality = Double.NEGATIVE_INFINITY;
        for (Position candidatePosition : candidatePositions) {
            double quality = calculateQuality(candidatePosition);
            if (quality > bestQuality) {
                bestQuality = quality;
                bestPosition = candidatePosition;
            }
        }
        return bestPosition;
    }

    private boolean isValidMove(int x, int y) {
        return x > 0 && x < maze.length - 1 && y > 0 && y < maze[0].length - 1 && maze[x][y].getValue() != ValueBox.WALL;
    }

    private double calculateQuality(Position position) {
        double distance = Math.sqrt(Math.pow(position.getX() - exitMaze.getX(), 2) +
                Math.pow(position.getY() - exitMaze.getY(), 2));
        return 1.0 / (1.0 + distance);
    }
}
