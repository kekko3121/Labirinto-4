package com.maze;

import com.maze.ABC.ArtificialBeeColony;
import com.maze.FactoryPattern.*;
import com.maze.Interactors.*;

public class Test {
    public static void main(String[] args) {
        Maze rmaze = new MazeDifficultyLevel().createMaze(Hardships.EASY);

        ArtificialBeeColony abc = new ArtificialBeeColony(rmaze.getMaze(), rmaze.getExitMaze());
        abc.run();
    }
}
