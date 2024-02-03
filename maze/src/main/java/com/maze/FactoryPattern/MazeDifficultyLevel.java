package com.maze.FactoryPattern;

import com.maze.Interactors.Hardships;

public class MazeDifficultyLevel extends MazeDifficulty{
    public Maze createMaze(Hardships hardship){
        switch(hardship){
            case EASY: // if the hardship is easy, return a new EasyMaze
                EasyMaze easyMaze = new EasyMaze();
                easyMaze.generateMaze();
                return easyMaze;
            case MEDIUM: // if the hardship is medium, return a new MediumMaze
                MediumMaze mediumMaze = new MediumMaze(); // create a new MediumMaze
                mediumMaze.generateMaze(); // generate the maze
                return mediumMaze;  // return the maze
            case HARD: // if the hardship is hard, return a new HardMaze
                HardMaze hardMaze = new HardMaze(); // create a new HardMaze
                hardMaze.generateMaze(); // generate the maze
                return hardMaze; // return the maze
            default: // if the hardship is not valid, throw an exception
                throw new IllegalArgumentException("Invalid hardship");
        }
    }
}
