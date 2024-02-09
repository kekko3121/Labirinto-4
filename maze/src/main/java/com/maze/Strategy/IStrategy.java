package com.maze.Strategy;

import com.maze.Interactors.Position;

public interface IStrategy {
    public Position nextMove(Position currentPosition);
}