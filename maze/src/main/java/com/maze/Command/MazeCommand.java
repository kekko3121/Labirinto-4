package com.maze.Command;

import com.maze.Interactors.Box;
import com.maze.Interactors.Hardships;
import com.maze.Interactors.Position;

public abstract class MazeCommand {

    public String name; // Nome del player
    public String surname; // Cognome del player
    public String nickname; // Nickname del player

    /**
     * Costruttore per passare i parametri del player e la difficoltà del labirinto
     * @param name
     * @param surname
     * @param nickname
     */
    public MazeCommand(String name, String surname, String nickname) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
    }

    /**
     * Metodo astratto per costruire graficamente il labirinto
     */

    public abstract void buildMaze(Box[][] maze, Position exPosition);
}