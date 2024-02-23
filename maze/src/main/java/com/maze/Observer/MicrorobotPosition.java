package com.maze.Observer;

import java.util.List;

import com.maze.Strategy.Microrobot;
import com.maze.Interactors.Box;

public interface MicrorobotPosition {
    public void update(List<Microrobot> microrobots, Box[][] maze);
}
