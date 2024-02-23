package com.maze.Observer;

import java.util.List;

import com.maze.Strategy.Microrobot;
import com.maze.Interactors.Box;

/**
 * Interfaccia per aggiornare la posizione dei microrobot all'interno del labirinto
 * @param microrobots lista dei microrobot
 * @param maze labirinto
 */
public interface MicrorobotPosition {
    /**
     * Aggiorna lo stato attuale del gioco
     * @param microrobots
     * @param maze
     */
    public void update(List<Microrobot> microrobots, Box[][] maze);
}
