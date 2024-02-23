package com.maze.Observer;

public interface Observable{
    public void subscribe(MicrorobotPosition observer);
    public void notifyObservers();
}