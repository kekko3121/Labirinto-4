package com.maze.FactoryPattern;

import java.util.Random;

/**
 * Class that represents the easy maze
 * It extends the Maze class
 * It generates the maze and the trapdoors
 */
public class EasyMaze extends Maze{
    /**
     * Constructor of the class EasyMaze that calls the constructor of the Maze class
     */
    public EasyMaze(){
        super(14); // call the constructor of the Maze class with the dimension of 14
    }
    
    /**
     * Generates the maze
     */
    @Override
    public void generateMaze(){
        Random random = new Random(); // random number generator to generate the maze
        for(int i = 0; i < getDim(); i++){ // iterate over the maze
            for(int j = 0; j < getDim(); j++){
                // if the random number is less than 0.05 and the position is not the exit
                if(random.nextDouble() < 0.05 && getExitMaze().getX() != i && getExitMaze().getY() != j){ 
                    generateRandomLine(i, j); //generate a random line wall in the maze
                }
            }
        }
        generateTrapdoors(2); // generate the trapdoors in the maze with a number of 2
    }
}
