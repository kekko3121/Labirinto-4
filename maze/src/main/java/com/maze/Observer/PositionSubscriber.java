package com.maze.Observer;

import java.util.List;

import com.maze.Interactors.Box;
import com.maze.Strategy.Microrobot;


public interface PositionSubscriber {
    /**
     * Metodo per aggiornare lo stato del gioco
     * @param maze Rappresenta il labirinto
     * @param microrobotInfo Lista di informazioni sui microrobot
     */
    public void update(Box[][] maze, List<Microrobot> microrobotInfo);
}
