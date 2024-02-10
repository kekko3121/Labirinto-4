package com.maze.State;

import com.maze.Interactors.Position;
import com.maze.Strategy.IStrategy;

public class OneMoveState implements IState{
    private IStrategy strategy;

    public OneMoveState(IStrategy strategy) {
        this.strategy = strategy;
    }

    public Position doAction(Position currentPosition) {
        return strategy.nextMove(currentPosition);
    }
}