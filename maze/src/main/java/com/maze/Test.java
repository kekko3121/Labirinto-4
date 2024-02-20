package com.maze;

import com.maze.Interactors.*;
import com.maze.Observer.*;

public class Test {

    public static void main(String[] args) {
        Game game = new Game(Hardships.EASY, 1); // Crea il gioco con un microrobot

        // Iscrizione al gioco per ricevere gli aggiornamenti sulle posizioni dei microrobot
        ConcreteGame concreteGame = new ConcreteGame();
        game.subscribe(concreteGame);

        // Movimento del microrobot nel labirinto
        System.out.println("Posizione iniziale del microrobot: " + game.getMicrorobotPosition(0).getX() + " " + game.getMicrorobotPosition(0).getY());
        while (!game.getMicrorobotPosition(0).equals(game.getExitMaze())) {
            game.moveMicrorobots(); // Muove il microrobot
        }
        System.out.println("Microrobot ha raggiunto l'uscita!");
    }
}
