package com.maze.Observer;

import java.util.ArrayList;
import java.util.List;

import com.maze.Strategy.Microrobot;
import com.maze.Interactors.Box;
import com.maze.Interactors.ValueBox;

/**
 * Classe per aggiornare lo stato del gioco e la posizione dei microrobot all'interno del labirinto
 */
public class UpdateGame implements MicrorobotPosition {

    private List<Microrobot> exMicrorobots; // lista dei microrobot precedenti
    private List<Microrobot> microrobots; // lista dei microrobot attuali
    private Box[][] maze; // labirinto

    /**
     * Costruttore per inizializzare le liste dei microrobot e il labirinto
     */
    public UpdateGame(){
        this.microrobots = new ArrayList<>();
        this.maze = null;
    }

    /**
     * Aggiorna lo stato attuale del gioco
     * @param microrobots lista dei microrobot
     * @param maze labirinto
     */
    public void update(List<Microrobot> microrobots, Box[][] maze) {
        this.exMicrorobots = this.microrobots;
        this.microrobots = microrobots;
        this.maze = maze;
    }

    /**
     * Restituisce la dimensione del labirinto
     * @return
     */
    public int getDim(){
        return this.maze.length;
    }

    /**
     * Restituisce il labirinto
     * @return
     */
    public Box[][] getMaze(){
        return this.maze;
    }
}
