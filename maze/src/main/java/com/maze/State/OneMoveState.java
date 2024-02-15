package com.maze.State;

import com.maze.Interactors.Position;
import com.maze.Strategy.IStrategy;

/**
 * Classe per muovere il microrobot di una casella alla volta.
 */
public class OneMoveState implements IState{

    private IStrategy strategy; // strategia per muovere il microrobot

    /**
     * Costruttore della classe per passare la strategia per muovere il microrobot.
     * @param strategy
     */
    public OneMoveState(IStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Metodo per muovere il microrobot di una casella alla volta.
     * @param currentPosition
     * @return la nuova posizione del microrobot
     */
    public Position doAction(Position currentPosition) {
        return strategy.nextMove(currentPosition);
    }
}