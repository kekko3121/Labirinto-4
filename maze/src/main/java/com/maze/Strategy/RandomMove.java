package com.maze.Strategy;

import java.util.Random;
import com.maze.Interactors.Position;

/**
 * Classe per muovere il microrobot in modo casuale.
 * @see IStrategy
 */
public class RandomMove implements IStrategy{

    private Random rand; // generatore di numeri casuali

    /**
     * Costruttore della classe per inizializzare il generatore di numeri casuali.
     */
    public RandomMove() {
        this.rand = new Random();
    }

    /**
     * Metodo per calcolare il prossimo movimento del microrobot in modo casuale.
     * @param currentPosition
     * @return la nuova posizione del microrobot
     */
    public Position nextMove(Position currentPosition){
        int moveX = rand.nextInt(3) - 1;
        int moveY = rand.nextInt(3) - 1;
        return new Position(currentPosition.getX() + moveX, currentPosition.getY() + moveY);
    }
}