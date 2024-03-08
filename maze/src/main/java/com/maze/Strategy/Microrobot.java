package com.maze.Strategy;

import com.maze.Interactors.Position;

/**
 * Classe che rappresenta un microrobot nel labirinto.
 */
public class Microrobot {
    
    private IStrategy microRobotStrategy; // Strategia del microrobot

    private Position actualPosition; // posizione attuale del microrobot

    /**
     * Costruttore della classe per passare la posizione attuale e lo stato del microrobot.
     * @param actualPosition
     * @param microRobotState
     */
    public Microrobot(Position actualPosition, IStrategy microRobotStrategy){
        this.actualPosition = actualPosition;
        this.microRobotStrategy = microRobotStrategy;
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
     * Cambia la strategia del microrobot.
     * @param microRobotStrategy
     */
    public void setMicroRobotStrategy(IStrategy microRobotStrategy){
        this.microRobotStrategy = microRobotStrategy;
    }

    /**
     * Restituisce la strategia del microrobot.
     * @return
     */
    public IStrategy getMicroRobotStrategy(){
        return this.microRobotStrategy;
    }

    /**
     * Metodo per muovere il microrobot.
     */
    public void move(){
        this.setActualPosition(this.microRobotStrategy.nextMove(this.getPosition()));
    }
}