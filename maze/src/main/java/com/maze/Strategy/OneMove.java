package com.maze.Strategy;

import com.maze.ABC.ArtificialBeeColony;
import com.maze.Interactors.*;

public class OneMove implements IStrategy{
    
    private ArtificialBeeColony abc;

    public OneMove(Box[][] maze, Position exitPosition){
        abc = new ArtificialBeeColony(maze, exitPosition);
    }

    public Position nextMove(Position currentPosition){
        return abc.moveMicrorobot(currentPosition);
    }
}
