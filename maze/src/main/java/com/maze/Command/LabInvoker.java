package com.maze.Command;

import javafx.event.Event;
import java.io.IOException;

/**
 * Classe invoker per memorizzare il comando affinché possa essere eseguito 
 */
public class LabInvoker {

    private MazeCommand command; // Comando da eseguire

    /**
     * Metodo per eseguire il comando
     * @param e
     * @param command
     * @throws IOException
     */
    public void execute(Event e, MazeCommand command) throws IOException {
        this.command = command;
        this.command.loadMaze(e);
    }
}
