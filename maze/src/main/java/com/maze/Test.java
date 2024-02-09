package com.maze;

import com.maze.FactoryPattern.*;
import com.maze.Interactors.Hardships;
import com.maze.Interactors.Position;
import com.maze.State.*;
import com.maze.Strategy.*;

public class Test {
    public static void main(String[] args) {
        // Creazione del labirinto di difficoltà media
        Maze mazeFactory = new MazeDifficultyLevel().createMaze(Hardships.EASY);
        
        // Posizione iniziale del microrobot nel labirinto
        Position initialPosition = new Position(0, 0);

        OneMoveState onemove = new OneMoveState(new OneMove(mazeFactory.getMaze(), mazeFactory.getExitMaze()));
        
        // Creazione del microrobot nel labirinto
        Microrobot microrobot = new Microrobot(initialPosition, (IState) onemove);
        
        // Simulazione del movimento del microrobot nel labirinto per 10 passi
        for (int i = 0; i < 10; i++) {
            
            // Aggiorna la posizione del microrobot
            microrobot.setActualPosition(microrobot.getMicroRobotState().doAction(microrobot.getPosition()));

            // Stampa la nuova posizione del microrobot
            System.out.println("Step " + (i + 1) + ": Microrobot Position: " + microrobot.getPosition());
        }
    }
}
