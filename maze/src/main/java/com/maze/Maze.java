package com.maze;

public abstract class Maze implements MazeInterface {

    private int [][] maze;
    private int dim;
    private int end;

    public Maze(int dim){
        maze = new int[dim][dim];
        this.dim = dim;
    }

    public int getDim(){
        return dim;
    }

    public abstract void createMaze();
}