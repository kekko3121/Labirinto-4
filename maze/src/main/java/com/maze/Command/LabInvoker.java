package com.maze.Command;

import com.maze.Interactors.Box;
import com.maze.Interactors.Position;

public class LabInvoker {

    private MazeCommand command;
    
    public void execute(Box[][] maze, Position exPosition, MazeCommand command){
        this.command = command;
        this.command.buildMaze(maze, exPosition);
    }
}
