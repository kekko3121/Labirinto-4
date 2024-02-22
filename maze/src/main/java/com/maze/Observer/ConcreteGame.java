package com.maze.Observer;

import com.maze.Interactors.Box;
import com.maze.Strategy.Microrobot;

import java.util.List;

/**
 * Classe per aggiornare lo stato del gioco
 * @see PositionSubscriber
 */
public class ConcreteGame implements PositionSubscriber {

    private Box[][] maze; // Rappresenta il labirinto
    private List<Microrobot> microrobotInfo; // Lista di informazioni sui microrobot

    public ConcreteGame(Box[][] maze, List<Microrobot> microrobotInfo) {
        this.maze = maze;
        this.microrobotInfo = microrobotInfo;
    }

    @Override
    public void update(Box[][] maze, List<Microrobot> microrobotInfo) {
        this.maze = maze;
        this.microrobotInfo = microrobotInfo;
    }
}
