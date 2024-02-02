package com.maze.FactoryPattern;

import java.util.Random;

import com.maze.Interactors.ValueBox;

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
        generateTrapdoors(); // generate the trapdoors in the maze
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
        Random random = new Random(); // random number generator to generate the trapdoors
        int trapdoorsPlaced = 0; // number of trapdoors placed
        while (trapdoorsPlaced < 2) { // while the number of trapdoors placed is less than 2
            int x = random.nextInt(getDim()); // generate a random x coordinate
            int y = random.nextInt(getDim()); // generate a random y coordinate
            // if the value of the box is empty and the position is not the exit
            if (getMaze()[x][y].getValue() == ValueBox.EMPTY && getExitMaze().getX() != x && getExitMaze().getY() != y){
                getMaze()[x][y].setValue(ValueBox.HATCH); // set the value of the box to HATCH
                trapdoorsPlaced++; // increment the number of trapdoors placed
            }
        }
    }
}