package com.maze.FactoryPattern;

import com.maze.Interactors.*;

public class Test {
    public static void main(String[] args) {
        Maze rmaze = new MazeDifficultyLevel().createMaze(Hardships.HARD);
        
        // Ottieni la matrice del labirinto
        Box[][] maze = rmaze.getMaze();
        
        // Ottieni la posizione dell'uscita
        Position exitPosition = rmaze.getExitMaze();

        // Stampare il labirinto
        for (int i = 0; i < rmaze.getDim(); i++) {
            for (int j = 0; j < rmaze.getDim(); j++) {
                // Verifica se la posizione corrente corrisponde all'uscita
                if (i == exitPosition.getX() && j == exitPosition.getY()) {
                    System.out.print("E "); // Stampa l'uscita del labirinto
                } else {
                    ValueBox value = maze[i][j].getValue();
                    // Utilizza caratteri specifici per rappresentare i diversi tipi di caselle
                    switch (value) {
                        case WALL:
                            System.out.print("# "); // Parete
                            break;
                        case EMPTY:
                            System.out.print(". "); // Casella vuota
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
