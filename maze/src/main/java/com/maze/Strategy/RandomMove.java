package com.maze.Strategy;

import java.util.Random;
import com.maze.Interactors.Position;

public class RandomMove {
    private Random rand;

    public RandomMove() {
        this.rand = new Random();
    }

    public Position nextMove(Position currentPosition){
        int moveX = rand.nextInt(3) - 1;
        int moveY = rand.nextInt(3) - 1;
        return new Position(currentPosition.getX() + moveX, currentPosition.getY() + moveY);
    }
}