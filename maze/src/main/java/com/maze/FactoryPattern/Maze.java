package com.maze.FactoryPattern;

import java.util.Random;

import com.maze.Interactors.*;

public abstract class Maze implements IMaze {
    
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
     * Generates a random line in the maze
     * @param startX
     * @param startY
     */
    protected void generateRandomLine(int startX, int startY){
        // generate a random line
        boolean horizontal = new Random().nextBoolean();
        int size = 2 + new Random().nextInt(5); // size of the line (2-6)
        if(horizontal){ // horizontal line
            for(int j = startY; j < Math.min(startY + size, getDim()); j++){ // min(startY + size, getDim()) to avoid out of bounds
                if (j < getDim()){ // check if the index is in the maze
                    getMaze()[startX][j].setValue(ValueBox.WALL); // set the value of the box to WALL
                }
            }
        }
        else{ // vertical line
            for(int i = startX; i < Math.min(startX + size, getDim()); i++){
                if (i < getDim()){
                    getMaze()[i][startY].setValue(ValueBox.WALL);
                }
            }
        }
    }

    /**
     * Generates the trapdoors in the maze
     */
    protected void generateTrapdoors(int ntrapdoors){
        Random random = new Random(); // random number generator to generate the trapdoors
        int trapdoorsPlaced = 0; // number of trapdoors placed
        while (trapdoorsPlaced < ntrapdoors) { // while the number of trapdoors placed
            int x = random.nextInt(getDim()); // generate a random x coordinate
            int y = random.nextInt(getDim()); // generate a random y coordinate
            // if the value of the box is empty and the position is not the exit
            if (getMaze()[x][y].getValue() == ValueBox.EMPTY && getExitMaze().getX() != x && getExitMaze().getY() != y){
                getMaze()[x][y].setValue(ValueBox.HATCH); // set the value of the box to HATCH
                trapdoorsPlaced++; // increment the number of trapdoors placed
            }
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