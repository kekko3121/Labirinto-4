package com.maze.Command;

import java.io.IOException;

import com.maze.HelloApplication;
import com.maze.MazeController;
import com.maze.Interactors.Hardships;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadHardMaze extends MazeCommand{

     /**
     * Costruttore per passare i parametri del player e il fxml da caricare
     * @param name
     * @param surname
     * @param nickname
     * @param fxml
     */
    public LoadHardMaze(String name, String surname, String nickname) {
        super(name, surname, nickname);
        this.hardships = Hardships.HARD;
    }

    /**
     * Metodo per eseguire il caricamento del labirinto
     * @param e
     */
    @Override
    public void loadMaze(Event e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hardMaze.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        MazeController maze = new MazeController(this);
        fxmlLoader.setController(maze);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setMaximized(true);
        stage.setResizable(false);
    }
}
