package com.maze.Strategy;

import com.maze.ABC.ArtificialBeeColony;
import com.maze.Interactors.*;

/**
 * Classe per calcolare il prossimo movimento del microrobot con l'algoritmo ABC.
 * @see IStrategy  
 */
public class OneMove implements IStrategy{
    
    private ArtificialBeeColony abc; // algoritmo ABC

    /**
     * Costruttore della classe per passare il labirinto e la posizione di uscita.
     * @param maze
     * @param exitPosition
     */
    public OneMove(Box[][] maze, Position exitPosition){
        abc = new ArtificialBeeColony(maze, exitPosition);
    }

    /**
     * Metodo per calcolare il prossimo movimento del microrobot con l'algoritmo ABC.
     * @param currentPosition
     * @return la nuova posizione del microrobot
     */
    public Position nextMove(Position currentPosition){
        return abc.moveMicrorobot(currentPosition);
    }
}
