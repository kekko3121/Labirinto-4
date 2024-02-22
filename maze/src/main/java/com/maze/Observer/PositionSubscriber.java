package com.maze.Observer;

import com.maze.Interactors.Position;
import com.maze.Interactors.Box;
import com.maze.Strategy.IStrategy;

/**
 * Interfaccia per rappresentare il Subscriber del pattern Observer
 */
public interface PositionSubscriber {
    /**
     * Metodo per aggiornare lo stato del gioco
     * @param maze Rapresenta il labirinto
     * @param position Rappresenta la posizione del microrobot
     * @param State Rappresenta lo stato del microrobot
     */
    public void update(Box[][] maze, Position position, IStrategy Strategy);
}
