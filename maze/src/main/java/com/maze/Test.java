package com.maze;

import com.maze.Interactors.*;
import com.maze.ABC.ArtificialBeeColony;
import com.maze.FactoryPattern.*;

public class Test {
    public static void main(String[] args) {
        Maze rmaze = new MazeDifficultyLevel().createMaze(Hardships.EASY);
        
        // Ottieni la matrice del labirinto
        Box[][] maze = rmaze.getMaze();
        
        // Ottieni la posizione dell'uscita
        Position exitPosition = rmaze.getExitMaze();
        
        // Inserisci il microrobot in una posizione casuale nel labirinto
        Position robotPosition = getRandomPosition(maze);

        ArtificialBeeColony abc = new ArtificialBeeColony(maze, exitPosition, maze.length);
        
        boolean robotExited = false;

        // Loop finché il robot non esce dal labirinto
        while (!robotExited) {
            abc.run(); // Esegui l'algoritmo per un ciclo

            // Verifica se la posizione del robot coincide con quella dell'uscita
            if (robotPosition.equals(exitPosition)) {
                robotExited = true;
            }

            // Stampare il labirinto con la posizione aggiornata del robot
            printMaze(maze, exitPosition, robotPosition);
        }
    }
    
    // Metodo per ottenere una posizione casuale nel labirinto
    private static Position getRandomPosition(Box[][] maze) {
        int x = (int) (Math.random() * maze.length);
        int y = (int) (Math.random() * maze[0].length);
        return new Position(x, y);
    }
    
    // Metodo per stampare il labirinto con l'uscita e la posizione del robot
    private static void printMaze(Box[][] maze, Position exitPosition, Position robotPosition) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == exitPosition.getX() && j == exitPosition.getY()) {
                    System.out.print("E "); // Uscita del labirinto
                } else if (i == robotPosition.getX() && j == robotPosition.getY()) {
                    System.out.print("R "); // Posizione del robot
                } else {
                    switch (maze[i][j].getValue()) {
                        case WALL:
                            System.out.print("# "); // Parete
                            break;
                        case EMPTY:
                            System.out.print(". "); // Spazio vuoto
                            break;
                        case HATCH:
                            System.out.print("O "); // Botola
                            break;
                    }
                }
            }
            System.out.println(); // Vai a capo alla fine di ogni riga
        }
    }
}