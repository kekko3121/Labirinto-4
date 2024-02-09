package com.maze.State;

import com.maze.Interactors.Position;

public class Microrobot {
    
    private IState microRobotState;

    private Position actualPosition;

    public Microrobot(Position actualPosition, IState microRobotState){
        this.actualPosition = actualPosition;
        this.microRobotState = microRobotState;
    }

    public void setActualPosition(Position actualPosition){
        this.actualPosition = actualPosition;
    }
    public Position getPosition(){
        return this.actualPosition;
    }

    public void setMicroRobotState(IState microRobotState){
        this.microRobotState = microRobotState;
    }

    public IState getMicroRobotState(){
        return this.microRobotState;
    }
}