package com.maze.Interactors;

/**
 * Classe che rappresenta una posizione nel labirinto.
 */
public class Position {

    private int x; // coordinate x

    private int y; // coordinate y

    /**
     * Costruttore della classe per passare le coordinate x e y della posizione.
     * @param x
     * @param y
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * imposta la coordinata x della posizione
     * @param x
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * imposta la coordinata y della posizione
     * @param y
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Restituisce la coordinata x
     * @return coordinata x
     */
    public int getX(){
        return x;
    }

    /**
     * Restituisce la coordinata y
     * @return coordinata y
     */
    public int getY(){
        return y;
    }
}