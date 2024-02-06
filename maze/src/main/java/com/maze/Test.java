package com.maze;

import com.maze.ABC.ArtificialBeeColony;
import com.maze.FactoryPattern.Maze;
import com.maze.FactoryPattern.MazeDifficultyLevel;
import com.maze.Interactors.*;

public class Test {
    public static void main(String[] args) {
        Maze rmaze = new MazeDifficultyLevel().createMaze(Hardships.EASY);
        
        // Ottieni la matrice del labirinto
        Box[][] maze = rmaze.getMaze();
        
        // Ottieni la posizione dell'uscita
        Position exitPosition = rmaze.getExitMaze();
        
        // Inserisci il microrobot in una posizione casuale nel labirinto

        ArtificialBeeColony abc = new ArtificialBeeColony(maze, exitPosition, maze.length);

        // Esegui l'algoritmo ABC
        abc.run();
    }
    
    // Metodo per ottenere una posizione casuale nel labirinto
    private static Position getRandomPosition(Box[][] maze) {
        int x = (int) (Math.random() * maze.length);
        int y = (int) (Math.random() * maze[0].length);
        return new Position(x, y);
    }
}