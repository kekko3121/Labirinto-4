package com.maze.State;

import com.maze.Interactors.Position;

public interface IState {
    public Position doAction(Position currentPosition);
}