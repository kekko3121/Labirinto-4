package com.maze.Strategy;

import com.maze.Interactors.Position;

public interface IStrategy {
    Position nextMove(Position currentPosition);
}
