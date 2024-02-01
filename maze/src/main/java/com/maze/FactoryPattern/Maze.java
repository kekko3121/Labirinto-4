package com.maze.FactoryPattern;

import java.util.Random;

import com.maze.Interactors.*;

public abstract class Maze implements MazeInterface {
    
    /**
     * Matrix that represents the maze
     */
    private Box [][] maze;
    
    /**
     * Dimension of the maze
     */
    private int dim;

    /**
     * Position of the exit of the maze
     */
    private Position exitMaze;

    /**
     * Initializes a maze with the given dimension
     * @param dim
     */
    public Maze(int dim){
        maze = new Box[dim][dim];
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                maze[i][j] = new Box(ValueBox.EMPTY, new Position(i,j));
            }
        }
        this.dim = dim;
        generateExit();
    }

    public Box[][] getMaze(){
        return maze;
    }

    /**
     * Returns the maze dimension
     * @return
     */
    public int getDim(){
        return dim;
    }

    /**
     * generate a random exit in the maze
     */
    private void generateExit(){
        int side = new Random().nextInt(4);
        switch (side) {
            case 0:
                exitMaze = new Position(0, new Random().nextInt(dim));
                break;
            case 1:
                exitMaze = new Position(dim-1, new Random().nextInt(dim));
                break;
            case 2:
                exitMaze = new Position(new Random().nextInt(dim), 0);
                break;
            case 3:
                exitMaze = new Position(new Random().nextInt(dim), dim-1);
                break;
            default:
                break;
        }
    }

    /**
     * Returns the exit of the maze
     * @return
     */
    public Position getExitMaze(){
        return exitMaze;
    }

    /**
     * abstract method that generates the maze,
     * it is implemented in the subclasses
     */
    public abstract void generateMaze();
}