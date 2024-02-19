package com.maze.Observer;

import com.maze.Interactors.Position;
import com.maze.Interactors.Box;
import com.maze.State.IState;

/**
 * Interfaccia per rappresentare il Subscriber del pattern Observer
 */
public interface PositionSubcriber {
    /**
     * Metodo per aggiornare lo stato del gioco
     * @param maze Rapresenta il labirinto
     * @param position Rappresenta la posizione del microrobot
     * @param State Rappresenta lo stato del microrobot
     */
    public void update(Box[][] maze, Position position, IState State);
}
