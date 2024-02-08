package com.maze.State;

import com.maze.Interactors.Box;
import com.maze.Interactors.Position;
import com.maze.Strategy.OneMove;

public class OneMoveState implements IState {

    private OneMove oneMoveStrategy;

    public OneMoveState(Box[][] maze, Position exitPosition) {
        this.oneMoveStrategy = new OneMove(maze, exitPosition);
    }

    @Override
    public void doAction(Box box) {
        Position currentPosition = box.getPosition();
        Position nextPosition = oneMoveStrategy.nextMove(currentPosition);
        box.setPosition(nextPosition);
    }
}