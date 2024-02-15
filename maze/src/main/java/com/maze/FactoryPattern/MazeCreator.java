package com.maze.FactoryPattern;

import com.maze.Interactors.Hardships;

/**
 * Classe astratta che implementa il creator del labirinto.
 */
public abstract class MazeCreator {
    public abstract Maze createMaze(Hardships hardship);
}
