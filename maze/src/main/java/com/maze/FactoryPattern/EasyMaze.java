package com.maze.FactoryPattern;

import java.util.Random;

import com.maze.Interactors.ValueBox;

public class EasyMaze extends Maze{
    public EasyMaze(){
        super(20);
    }
    
    @Override
    public void generateMaze(){
        Random random = new Random();
        for(int i = 0; i < getDim(); i++){
            for(int j = 0; j < getDim(); j++){
                if(random.nextDouble() < 0.05 && getExitMaze().getX() != i && getExitMaze().getY() != j){
                    generateRandomLine(i, j);
                }
            }
        }
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
}
