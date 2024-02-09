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
     * Set the x coordinate of the position
     * @param x coordinate
    */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Set the y coordinate of the position
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

    /**
     * Compares two positions and returns true if they are the same
     * @param obj
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Position.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    /**
     * Calculates the distance between two positions
     * @param position
     */
    public double distance(Position other){
        int deltaX = this.x - other.x; // x2 - x1
        int deltaY = this.y - other.y; // y2 - y1
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY); // sqrt((x2 - x1)^2 + (y2 - y1)^2)
    }
}