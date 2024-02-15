package com.maze;

import com.maze.FactoryPattern.*;
import com.maze.Interactors.*;
import com.maze.State.*;
import com.maze.Strategy.*;

public class Test {

    public static void main(String[] args) {
        Maze rmaze = new MazeDifficulty().createMaze(Hardships.EASY);

        // Creazione del microrobot
        Microrobot microrobot = new Microrobot(new Position(1, 4), new OneMoveState(new OneMove(rmaze.getMaze(), rmaze.getExitMaze())));

        // Movimento del microrobot nel labirinto
        System.out.println("Posizione iniziale del microrobot: " + microrobot.getPosition().getX() + " " + microrobot.getPosition().getY());
        while (!microrobot.getPosition().equals(rmaze.getExitMaze())) {
            Position nextPosition = microrobot.getMicroRobotState().doAction(microrobot.getPosition());
            microrobot.setActualPosition(nextPosition);
            System.out.println("Microrobot si è spostato a: " + microrobot.getPosition().getX() + " " + microrobot.getPosition().getY());
        }
        System.out.println("Microrobot ha raggiunto l'uscita!");
    }
}
