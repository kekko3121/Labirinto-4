package com.maze.Strategy;

import java.util.List;

import com.maze.ABC.ArtificialBeeColony;
import com.maze.Interactors.*;

/**
 * Classe per calcolare il prossimo movimento del microrobot con l'algoritmo ABC.
 * @see IStrategy  
 */
public class OneMove implements IStrategy{
    
    private ArtificialBeeColony abc; // algoritmo ABC
    private List<Position> pathToExit; // percorso per uscire dal labirinto

    /**
     * Costruttore della classe per passare il labirinto e la posizione di uscita.
     * @param maze
     * @param exitPosition
     */
    public OneMove(Box[][] maze, Position exitPosition){
        abc = new ArtificialBeeColony(maze, exitPosition);
        pathToExit = null;
    }

     /**
     * Metodo per calcolare il prossimo movimento del microrobot con l'algoritmo ABC.
     * @param currentPosition
     * @return la nuova posizione del microrobot
     */
    public Position nextMove(Position currentPosition){
        if (pathToExit == null || pathToExit.isEmpty()) {
            // Se il percorso non è stato ancora calcolato o è vuoto, calcola il percorso
            pathToExit = abc.calculateMicrorobotPath(currentPosition);
        }
        
        // Restituisci la prossima posizione nel percorso
        if (!pathToExit.isEmpty()) {
            return pathToExit.remove(0); // Rimuove e restituisce il primo elemento del percorso
        } else {
            // Se il percorso è vuoto, restituisci la posizione corrente
            return currentPosition;
        }
    }
}
