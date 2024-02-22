package com.maze.Observer;

import com.maze.Interactors.Position;
import com.maze.Strategy.IStrategy;
import com.maze.Interactors.Box;

/**
 * Classe per rappresentare il Concrete Subsriber del pattern Observer.
 * Gestisce gli eventi di gioco e notifica i vari iscritti.
 * Aggiorna la posizione e la strategia del microrobot, il labirinto e la sua dimensione.
 */
public class ConcreteGame implements PositionSubscriber{
    
    private Position position; // posizione del microrobot

    private Position prevPosition; // posizione precedente del microrobot

    private Box[][] maze; // labirinto

    private IStrategy strategy; // stato del microrobot

    /**
     * Restituisce il labirinto
     * @return
     */
    public Box[][] getMaze(){
        return maze;
    }

    /**
     * Restituisce la dimensione del labirinto
     * @return
     */
    public int getDim(){
        return maze.length;
    }

    /**
     * Aggiorna la posizione e la strategia del microrobot e il labirinto.
     * @param maze
     * @param position
     * @param state
     */
    public void update(Box[][] maze, Position position, IStrategy strategy){
        this.prevPosition = this.position;
        this.maze = maze;
        this.position = position;
        this.strategy = strategy;
    }

    /**
     * Restituisce la differenza tra la posizione attuale e quella precedente del microrobot.
     * @return
     */
    public Position getPositionUpdate() {
        int deltaX = position.getX() - prevPosition.getX();
        int deltaY = position.getY() - prevPosition.getY();
    
        return new Position(deltaX, deltaY);
    }
}
