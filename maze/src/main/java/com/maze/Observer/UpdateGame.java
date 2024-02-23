package com.maze.Observer;

import java.util.ArrayList;
import java.util.List;

import com.maze.Strategy.Microrobot;
import com.maze.Interactors.Box;
import com.maze.Interactors.ValueBox;

public class UpdateGame implements MicrorobotPosition {
    private List<Microrobot> exMicrorobots;
    private List<Microrobot> microrobots;
    private Box[][] maze;

    public UpdateGame(){
        this.microrobots = new ArrayList<>();
        this.maze = null;
    }

    public void update(List<Microrobot> microrobots, Box[][] maze) {
        this.exMicrorobots = this.microrobots;
        this.microrobots = microrobots;
        this.maze = maze;

        for(Microrobot microrobot : exMicrorobots){
            if(microrobot != null && maze[microrobot.getPosition().getX()][microrobot.getPosition().getY()].getValue() != ValueBox.HATCH){
                maze[microrobot.getPosition().getX()][microrobot.getPosition().getY()].setValue(ValueBox.EMPTY);
            }
        }

        for(Microrobot microrobot : microrobots){
            if (maze[microrobot.getPosition().getX()][microrobot.getPosition().getY()].getValue() != ValueBox.HATCH){
                maze[microrobot.getPosition().getX()][microrobot.getPosition().getY()].setValue(ValueBox.OCCUPIED);   
            }
        }
    }

    public int getDim(){
        return this.maze.length;
    }

    public Box[][] getMaze(){
        return this.maze;
    }
}
