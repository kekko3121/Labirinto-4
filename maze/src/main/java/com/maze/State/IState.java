package com.maze.State;

import com.maze.Interactors.Box;
import com.maze.Interactors.Position;

public interface IState {
    public void doAction(Box box);
}