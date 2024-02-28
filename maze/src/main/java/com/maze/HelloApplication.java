package com.maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homepageswarm.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setResizable(false);
            primaryStage.setTitle("MICROROBOT MAZE EXPLORER : SWARM EDITION");
            primaryStage.setScene(scene);
            primaryStage.show();
        }


    public static void main(String[] args) {
        launch(args);
    }
}
