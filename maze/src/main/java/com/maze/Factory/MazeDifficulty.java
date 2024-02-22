package com.maze.Factory;

import com.maze.Interactors.Hardships;

/**
 * Classe che implementa la difficoltà del labirinto.
 * La classe MazeDifficulty è una classe che estende la classe MazeCreator e implementa il metodo astratto createMaze.
 * @see MazeCreator
 */
public class MazeDifficulty extends MazeCreator{

    /**
     * Metodo che crea il labirinto in base alla difficoltà.
     * @param hardship la difficoltà del labirinto
     * @return il labirinto
     */
    public Maze createMaze(Hardships hardship){
        switch(hardship){
            case EASY: // se la difficoltà è facile, restituisci un nuovo labirinto EasyMaze
                EasyMaze easyMaze = new EasyMaze(); // crea un nuovo labirinto EasyMaze
                easyMaze.generateMaze(); // genera il labirinto
                return easyMaze; // restituisci il labirinto
            case MEDIUM: // se la difficoltà è media, restituisci un nuovo labirinto MediumMaze
                MediumMaze mediumMaze = new MediumMaze(); // crea un nuovo labirinto MediumMaze
                mediumMaze.generateMaze();
                return mediumMaze;
            case HARD: // se la difficoltà è difficile, restituisci un nuovo labirinto HardMaze
                HardMaze hardMaze = new HardMaze(); // crea un nuovo labirinto HardMaze
                hardMaze.generateMaze();
                return hardMaze;
            default: // se la difficoltà non è valida, lancia un'eccezione
                throw new IllegalArgumentException("Invalid hardship"); // lancia un'eccezione
        }
    }
}