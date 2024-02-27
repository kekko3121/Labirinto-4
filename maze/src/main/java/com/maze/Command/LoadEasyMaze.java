package com.maze.Command;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

import com.maze.Interactors.Hardships;
import com.maze.HelloApplication;
import com.maze.MazeController;

/**
 * Classe per rappresentare il caricamento del labirinto facile
 */
public class LoadEasyMaze extends MazeCommand {

    /**
     * Costruttore per passare i parametri del player e il fxml da caricare
     * @param name
     * @param surname
     * @param nickname
     * @param fxml
     */
    public LoadEasyMaze(String name, String surname, String nickname, String fxml) {
        super(name, surname, nickname, fxml);
        this.hardships = Hardships.EASY;
    }

    /**
     * Metodo per eseguire il caricamento del labirinto
     * @param e
     */
    @Override
    public void loadMaze(Event e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("easyMaze.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        MazeController maze = new MazeController(this);
        fxmlLoader.setController(maze);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setMaximized(true);
        stage.setResizable(false);
    }
}