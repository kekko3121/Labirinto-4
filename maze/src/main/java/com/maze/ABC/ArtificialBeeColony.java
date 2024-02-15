package com.maze.ABC;

import com.maze.Interactors.*;

import java.util.Random;

/**
 * Classe che implementa l'algoritmo dell'Artificial Bee Colony (ABC) per la ricerca del percorso
 * ottimale nel labirinto.
 * L'ABC è un algoritmo di ottimizzazione basato su colonie di api artificiali.
 */
public class ArtificialBeeColony {
    private static final int NUM_BEES = 10; // Numero di "api"

    private final Random rand; // Generatore di numeri casuali
    private final Position exitMaze; // Posizione dell'uscita nel labirinto
    private final Box[][] maze; // Labirinto

    /**
     * Costruttore della classe per passare il labirinto e la posizione dell'uscita.
     * @param maze Labirinto.
     * @param exitMaze Posizione dell'uscita.
     */
    public ArtificialBeeColony(Box[][] maze, Position exitMaze) {
        this.maze = maze;
        this.exitMaze = exitMaze;
        this.rand = new Random();
    }

    /**
     * Metodo che implementa l'algoritmo ABC per la ricerca del percorso ottimale nel labirinto.
     * L'algoritmo parte dalla posizione iniziale del microrobot e cerca di muoversi verso l'uscita del labirinto.
     */
    public Position moveMicrorobot(Position currentPosition) {
        Position bestPosition = currentPosition; // Posizione migliore
        double bestQuality = calculateQuality(currentPosition); // Qualità della posizione migliore

        for (int i = 0; i < NUM_BEES; i++) { // Per ogni "ape"
            Position newPosition = generateRandomMove(currentPosition); // Genera una mossa casuale
            double newQuality = calculateQuality(newPosition); // Calcola la qualità della nuova posizione
            if (newQuality > bestQuality) { // Se la nuova posizione è migliore della posizione migliore
                bestQuality = newQuality; // Aggiorna la qualità della posizione migliore
                bestPosition = newPosition; // Aggiorna la posizione migliore
            }
        }
        return bestPosition; // Ritorna la posizione migliore
    }

    /**
     * Metodo che genera una mossa casuale per il microrobot.
     * @param currentPosition Posizione corrente del microrobot.
     * @return La nuova posizione del microrobot.
     */
    private Position generateRandomMove(Position currentPosition) {
        int moveX = rand.nextInt(3) - 1; // Genera un numero casuale tra -1 e 1 per la coordinata x
        int moveY = rand.nextInt(3) - 1; // Genera un numero casuale tra -1 e 1 per la coordinata y

        int newX = currentPosition.getX() + moveX; // Calcola la nuova coordinata x
        int newY = currentPosition.getY() + moveY; // Calcola la nuova coordinata y

        if (isValidPosition(newX, newY)) { // Se la nuova posizione è valida
            return new Position(newX, newY); // Ritorna la nuova posizione
        } else {
            // Se la nuova posizione non è valida, ritorna la posizione corrente
            return currentPosition;
        }
    }

    /**
     * Metodo che verifica se una posizione è valida.
     * @param x Coordinata x.
     * @param y Coordinata y.
     * @return true se la posizione è valida, false altrimenti.
     */
    private boolean isValidPosition(int x, int y) {
        // Verifica se la posizione è all'interno del labirinto e non è un muro
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y].getValue() != ValueBox.WALL;
    }

    /**
     * Metodo che calcola la qualità di una posizione.
     * @param position Posizione.
     * @return La qualità della posizione.
     */
    private double calculateQuality(Position position) {
        // Calcola la distanza euclidea tra la posizione e l'uscita del labirinto
        double distance = Math.sqrt(Math.pow(position.getX() - exitMaze.getX(), 2) + Math.pow(position.getY() - exitMaze.getY(), 2));
        // Ritorna la qualità della posizione
        return 1.0 / (1.0 + distance);
    }
}