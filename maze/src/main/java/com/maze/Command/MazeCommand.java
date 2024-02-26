package com.maze.Command;

import java.io.IOException;

import com.maze.Interactors.Hardships;

import javafx.event.Event;

/**
 * Classe astratta per rappresentare il pattern Command
 */
public abstract class MazeCommand {

    private String name; // Nome del player
    private String surname; // Cognome del player
    private String nickname; // Nickname del player
    private Hardships hardships; // Difficoltà del labirinto

    /**
     * Costruttore per passare i parametri del player e la difficoltà del labirinto
     * @param name
     * @param surname
     * @param nickname
     * @param hardships
     */
    public MazeCommand(String name, String surname, String nickname, Hardships hardships) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.hardships = hardships;
    }

    /**
     * Metodo per eseguire il caricamento del labirinto
     * @param e
     */
    public abstract void loadMaze(Event e) throws IOException;
}
