package com.maze.FactoryPattern;

import com.maze.Interactors.Hardships;

public abstract class MazeDifficulty {
    public abstract Maze createMaze(Hardships hardship);
}
