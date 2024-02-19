package com.maze.State;

import com.maze.Interactors.Position;

/**
 * Classe che rappresenta un microrobot nel labirinto.
 */
public class Microrobot {
    
    private IState microRobotState; // stato del microrobot

    private Position actualPosition; // posizione attuale del microrobot

    /**
     * Costruttore della classe per passare la posizione attuale e lo stato del microrobot.
     * @param actualPosition
     * @param microRobotState
     */
    public Microrobot(Position actualPosition, IState microRobotState){
        this.actualPosition = actualPosition;
        this.microRobotState = microRobotState;
    }

    /**
     * Cambia la posizione del microrobot.
     * @param microRobotState
     */
    public void setActualPosition(Position actualPosition){
        this.actualPosition = actualPosition;
    }

    /**
     * Restituisce la posizione attuale del microrobot.
     * @return
     */
    public Position getPosition(){
        return this.actualPosition;
    }

    /**
     * Cambia lo stato del microrobot.
     * @param microRobotState
     */
    public void setMicroRobotState(IState microRobotState){
        this.microRobotState = microRobotState;
    }

    /**
     * Restituisce lo stato del microrobot.
     * @return
     */
    public IState getMicroRobotState(){
        return this.microRobotState;
    }

    /**
     * Metodo per muovere il microrobot.
     * @param microRobotState
     */
    public void move(){
        this.setActualPosition(this.microRobotState.doAction(actualPosition));
    }
}