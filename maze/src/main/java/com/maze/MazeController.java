package com.maze;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.scene.control.ProgressBar;


public class MazeController {

    @FXML
    private Button helpButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button readyButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button multiplayerButton;

    @FXML
    private Button playButton;

    @FXML
    private ImageView swarmRobot;

    @FXML
    private ProgressBar progressBar;

    @FXML
    TextField name;
    @FXML
    javafx.scene.control.TextField lastName;

    @FXML
    private ChoiceBox<String> level;





    @FXML
    private void showPlaypage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("playpage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) playButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //questo metodo permette di aprire la documentazione online del progetto presente su GitHub
    //Tramite il click del bottone help
    @FXML
    void showGithub(ActionEvent event) {
            String url = "https://github.com/kekko3121/Labirinto-4";//ulr repository github

            try {
                Desktop.getDesktop().browse(new URI(url));// Apre il link del repository GitHub nel browser predefinito
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();// Stampiamo lo stack trace dell'eccezione
                 /*
                 Lo stack trace,  è un elenco dettagliato delle chiamate ai metodi che
                 sono state eseguite fino al punto in cui si è verificata un'eccezione.
                  Include le classi, i metodi e le righe di codice in cui è avvenuta l'eccezione.
                */

            }
    }
    @FXML
    void showMultipage(ActionEvent event) {

    }
//questo metodo ci permette di chiudere la finestra di navigazione quando si preme il pulsante QUIT
    @FXML
    void quit(ActionEvent event) {
        Node source = (Node) event.getSource();// Ottiene il nodo sorgente dell'evento
        Stage stage = (Stage) source.getScene().getWindow();// Ottiene lo stage (finestra) corrispondente al nodo sorgente
        stage.close();// Chiude la finestra corrente
    }


    @FXML
    private void returntoHome(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepageswarm.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) returnButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void goReady(ActionEvent event) {
        try {
            // Carica la pagina dei punteggi
            FXMLLoader scoreLoader = new FXMLLoader(getClass().getResource("score.fxml"));
            Parent scoreRoot = scoreLoader.load();
            MazeController scoreController = scoreLoader.getController();
            scoreController.startProgressBar();
            Stage stage = (Stage) readyButton.getScene().getWindow();
            Scene scoreScene = new Scene(scoreRoot);

            // Ottieni la difficoltà selezionata
            String selectedLevel = level.getValue().toString();

            // Imposta la pagina del labirinto in base alla difficoltà selezionata
            String mazePage = "";
            if (selectedLevel.equals("Easy")) {
                mazePage = "easyMaze.fxml";
            } else if (selectedLevel.equals("Medium")) {
                mazePage = "mediumMaze.fxml";
            } else if (selectedLevel.equals("Hard")) {
                mazePage = "hardMaze.fxml";
            }

            // Carica la pagina del labirinto corrispondente
            FXMLLoader mazeLoader = new FXMLLoader(getClass().getResource(mazePage));
            Parent mazeRoot = mazeLoader.load();
            Scene mazeScene = new Scene(mazeRoot);

            // Mostra la pagina dei punteggi per 10 secondi
            stage.setScene(scoreScene);
            stage.show();

            // Passati 10 secondi, passa alla pagina del labirinto
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), e -> {
                stage.setScene(mazeScene);
                stage.show();
            }));
            timeline.play();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void startProgressBar() {
        // Crea un nuovo Thread per l'incremento automatico
        Thread thread = new Thread(() -> {
            try {
                // Incrementa la ProgressBar ogni secondo fino a raggiungere il massimo
                for (double progress = 0.0; progress <= 1.0; progress += 0.1) {
                    // Aggiorna la ProgressBar sulla JavaFX Application Thread
                    progressBar.setProgress(progress);

                    // Dormi il Thread per un secondo
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Imposta il thread come daemon per farlo terminare quando l'applicazione termina
        thread.setDaemon(true);

        // Avvia il thread
        thread.start();
    }

    @FXML
    public void controllerText1(){
        TextManager.textLength(name);
        buttonManager(lastName,name );

    }

    /**
     * Metodo per il controllo della corretta compilazione del campo "Cognome" nel main menu.*/
    @FXML
    public void controllerText2(){
        TextManager.textLength(lastName);
        buttonManager(name, lastName);

    }

    public void buttonManager(TextField text1, TextField text2)
    {
        if(TextManager.textConstraints(text1, text2))
        {
            readyButton.setDisable(true);
        }
        else
        {
            readyButton.setDisable(false);
        }
    }
}
