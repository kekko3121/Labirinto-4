package com.maze.FactoryPattern;

import java.util.Random;

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
                    generateRandomLine(i, j); //generate a random line wall in the maze
                }
            }
        }
        generateTrapdoors(7); // generate the trapdoors in the maze with a number of 7
    }
}
