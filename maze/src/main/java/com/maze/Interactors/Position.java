package com.maze.Interactors;

/**
 * Class that represents a position in the maze (x,y)
 */
public class Position {

    /**
     * x coordinate
     */
    private int x;
    
    /**
     * y coordinate
     */
    private int y;

    /**
     * Initializes a position with the given coordinates
     * @param x coordinate
     * @param y coordinate
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Initializes a position with the given position
     * @param x position
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Initializes a position with the given position
     * @param y position
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Returns the x coordinate
     * @return x coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the y coordinate
     * @return y coordinate
     */
    public int getY(){
        return y;
    }
}