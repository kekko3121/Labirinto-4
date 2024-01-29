package com.maze;

import java.util.Random;

public abstract class Maze implements MazeInterface {

    private int [][] maze;
    private int dim;
    private int exitMaze;

    public Maze(int dim){
        maze = new int[dim][dim];
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                maze[i][j] = 0;
            }
        }
        this.dim = dim;
    }

    public int getDim(){
        return dim;
    }

    public void generateExit(){
        int side = new Random().nextInt(4);
        switch(side){
            case 0:
                exitMaze = new Random().nextInt(dim);
                maze[0][exitMaze] = 1;
                break;
            case 1:
                exitMaze = new Random().nextInt(dim);
                maze[dim-1][exitMaze] = 1;
                break;
            case 2:
                exitMaze = new Random().nextInt(dim);
                maze[exitMaze][0] = 1;
                break;
            case 3:
                exitMaze = new Random().nextInt(dim);
                maze[exitMaze][dim-1] = 1;
                break;
        }
    }

    public abstract void generateMaze();
}