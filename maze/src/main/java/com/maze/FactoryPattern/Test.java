package com.maze.FactoryPattern;

import com.maze.Interactors.*;

public class Test {
    public static void main(String[] args) {
        EasyMaze easyMaze = new EasyMaze();
        easyMaze.generateMaze();

        // Ottieni la matrice del labirinto
        Box[][] maze = easyMaze.getMaze();
        
        // Ottieni la posizione dell'uscita
        Position exitPosition = easyMaze.getExitMaze();

        // Stampare il labirinto
        for (int i = 0; i < easyMaze.getDim(); i++) {
            for (int j = 0; j < easyMaze.getDim(); j++) {
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
