package com.maze.State;

import com.maze.Interactors.Position;

/**
 * Interfaccia che rappresenta lo stato di un microrobot nel labirinto.
 */
public interface IState {
    public Position doAction(Position currentPosition);
}