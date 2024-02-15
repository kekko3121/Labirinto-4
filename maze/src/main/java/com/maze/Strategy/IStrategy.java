package com.maze.Strategy;

import com.maze.Interactors.Position;

/**
 * Interfaccia per la strategia di movimento del microrobot.
 */
public interface IStrategy {
    public Position nextMove(Position currentPosition);
}