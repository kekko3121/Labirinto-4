package com.maze.State;

import com.maze.Interactors.Box;
import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;

public class Microrobot {
    
    private IState microRobotState;

    private Box actualBox;

    public Microrobot(Box box, IState microRobotState){
        this.actualBox = box;
        this.microRobotState = microRobotState;
    }

    public void setActualBox(Box box){
        this.actualBox = box;
    }

    public Box getActualBox(){
        return this.actualBox;
    }

    public Position getPosition(){
        return this.actualBox.getPosition();
    }

    public ValueBox getValue(){
        return this.actualBox.getValue();
    }

    public void setmicroRobotState(IState microRobotState){
        this.microRobotState = microRobotState;
    }

    public IState getmicroRobotState(){
        return this.microRobotState;
    }

    public void move(Box box){
        this.microRobotState.doAction(box);
    }
}
