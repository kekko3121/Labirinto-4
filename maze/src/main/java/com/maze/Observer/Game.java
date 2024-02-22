package com.maze.Observer;

import com.maze.Factory.Maze;
import com.maze.Interactors.Box;
import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;
import com.maze.Strategy.Microrobot;
import com.maze.Strategy.OneMove;
import com.maze.Strategy.IStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Observable {

    private final Maze maze;
    private final List<PositionSubscriber> subscribers;
    private final List<Microrobot> microrobots;

    public Game(Maze maze) {
        this.maze = maze;
        this.subscribers = new ArrayList<>();
        this.microrobots = new ArrayList<>();
    }

    @Override
    public void subscribe(PositionSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (PositionSubscriber subscriber : subscribers) {
            subscriber.update(maze.getMaze(), microrobots);
        }
    }

    public void initializeMicrorobots(int numMicrorobots) {
        for (int i = 0; i < numMicrorobots; i++) {
            Position randomPosition = getRandomEmptyPosition();
            if (randomPosition != null) {
                IStrategy strategy = new OneMove(maze.getMaze(), maze.getExitMaze());
                Microrobot microrobot = new Microrobot(randomPosition, strategy);
                microrobots.add(microrobot);
            }
        }
    }

    public void moveMicrorobots() {
        for (Microrobot microrobot : microrobots) {
            Position currentPosition = microrobot.getPosition();
            Position nextPosition = microrobot.getMicroRobotStrategy().nextMove(currentPosition);

            if (isValidMove(nextPosition)) {
                microrobot.setActualPosition(nextPosition);;
                // Check if microrobot landed on a trap
                if (maze.getMaze()[nextPosition.getX()][nextPosition.getY()].getValue() == ValueBox.TRAP) {
                    Position newPos = getRandomEmptyPosition();
                    if (newPos != null) {
                        microrobot.setActualPosition(newPos);
                    }
                }
            }
        }
        notifySubscribers();
    }

    private boolean isValidMove(Position position) {
        if (position.getX() < 0 || position.getX() >= maze.getDim() ||
            position.getY() < 0 || position.getY() >= maze.getDim()) {
            return false; // Out of bounds
        }
        Box box = maze.getMaze()[position.getX()][position.getY()];
        return box.getValue() != ValueBox.WALL && !isMicrorobotAtPosition(position);
    }

    private boolean isMicrorobotAtPosition(Position position) {
        for (Microrobot microrobot : microrobots) {
            if (microrobot.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    private Position getRandomEmptyPosition() {
        List<Position> emptyPositions = new ArrayList<>();
        for (int i = 0; i < maze.getDim(); i++) {
            for (int j = 0; j < maze.getDim(); j++) {
                if (maze.getMaze()[i][j].getValue() != ValueBox.WALL && !isMicrorobotAtPosition(new Position(i, j))) {
                    emptyPositions.add(new Position(i, j));
                }
            }
        }
        if (emptyPositions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return emptyPositions.get(random.nextInt(emptyPositions.size()));
    }
}
