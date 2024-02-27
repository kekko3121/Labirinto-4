package com.maze.Command;

import java.io.IOException;

import com.maze.Interactors.Hardships;

import javafx.event.Event;

/**
 * Classe astratta per rappresentare il pattern Command
 */
public abstract class MazeCommand {

    public String name; // Nome del player
    public String surname; // Cognome del player
    public String nickname; // Nickname del player
    public String fxml; // FXML da caricare
    public Hardships hardships; // Difficoltà del labirinto

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
