package com.progiii.progetto.progetto;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.control.ProgressBar;


public class HelloController {

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("score.fxml"));
            Parent root = fxmlLoader.load();
            HelloController controller = fxmlLoader.getController();
            controller.startProgressBar();
            Stage stage = (Stage) readyButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();



            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), e -> {
                try {
                    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("homepageswarm.fxml"));
                    Parent newRoot = newLoader.load();
                    Scene newScene = new Scene(newRoot);
                    stage.setScene(newScene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
}
