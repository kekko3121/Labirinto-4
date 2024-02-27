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
    private String fxml; // FXML da caricare
    protected Hardships hardships; // Difficoltà del labirinto

    /**
     * Costruttore per passare i parametri del player e la difficoltà del labirinto
     * @param name
     * @param surname
     * @param nickname
     * @param hardships
     */
    public MazeCommand(String name, String surname, String nickname, String fxml) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.fxml = fxml;
    }

    /**
     * Metodo per eseguire il caricamento del labirinto
     * @param e
     */
    public abstract void loadMaze(Event e) throws IOException;
}
