package com.maze.Observer;

import com.maze.Interactors.Position;

import com.maze.Interactors.Box;
import com.maze.State.IState;
import com.maze.State.OneMoveState;

/**
 * Classe per rappresentare il Concrete Subsriber del pattern Observer.
 * Gestisce gli eventi di gioco e notifica i vari iscritti.
 * Aggiorna la posizione e lo stato del microrobot, il labirinto e la sua dimensione.
 */
public class ConcreteGame implements PositionSubcriber{
    
    private Position position; // posizione del microrobot

    private Position prevPosition; // posizione precedente del microrobot

    private Box[][] maze; // labirinto

    private IState state; // stato del microrobot

    /**
     * Restituisce il labirinto
     * @return
     */
    public Box[][] getMaze(){
        return maze;
    }

    /**
     * Restituisce lo stato del microrobot
     * @return
     */
    public String getState(){
        if(state instanceof OneMoveState){
            return "Microrobot state OneMoveState";
        }
        else{
            return "Microrobot state nothing";
        }
    }

    /**
     * Restituisce la dimensione del labirinto
     * @return
     */
    public int getDim(){
        return maze.length;
    }

    /**
     * Aggiorna la posizione e lo stato del microrobot
     * @param maze
     * @param position
     * @param state
     */
    public void update(Box[][] maze, Position position, IState state){
        this.prevPosition = this.position;
        this.maze = maze;
        this.position = position;
        this.state = state;
    }

    public Position getPositionUpdate(){
        Position newPosition = new Position(0, 0);

        newPosition.setX(position.getX() - prevPosition.getX());
        newPosition.setY(position.getY() - prevPosition.getY());

        return newPosition;
    }
}
