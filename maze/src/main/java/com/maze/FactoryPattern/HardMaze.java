package com.maze.FactoryPattern;

import java.util.Random;
import com.maze.Interactors.ValueBox;

/**
 * Class that represents the hard maze
 * It extends the Maze class
 * It generates the maze and the trapdoors
 */
public class HardMaze extends Maze{
    /**
     * Constructor of the class HardMaze that calls the constructor of the Maze class
     */
    public HardMaze(){
        super(14); // call the constructor of the Maze class with the dimension of 14
    }

    /**
     * Generates the maze
     */
    public void generateMaze(){
        Random random = new Random();
        for(int i = 0; i < getDim(); i++){
            for(int j = 0; j < getDim(); j++){
                if(random.nextDouble() < 0.05 && getExitMaze().getX() != i && getExitMaze().getY() != j){
                    generateRandomLine(i, j);
                }
            }
        }
        generateTrapdoors();
    }

    /**
     * Generates a random line in the maze
     * @param startX
     * @param startY
     */
    private void generateRandomLine(int startX, int startY){
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
    private void generateTrapdoors(){
        Random random = new Random();
        for(int i = 0; i < getDim(); i++){
            for(int j = 0; j < getDim(); j++){
                if(random.nextDouble() < 0.05 && getExitMaze().getX() != i && getExitMaze().getY() != j){
                    getMaze()[i][j].setValue(ValueBox.HATCH);
                }
            }
        }
    }
}
