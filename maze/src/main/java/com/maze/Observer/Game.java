package com.maze.Observer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.maze.FactoryPattern.Maze;
import com.maze.FactoryPattern.MazeDifficulty;
import com.maze.Interactors.Hardships;
import com.maze.Interactors.Position;
import com.maze.State.Microrobot;
import com.maze.State.OneMoveState;
import com.maze.Strategy.OneMove;
import com.maze.Interactors.Box;

/**
 * Classe per gestire il gioco,
 * crea un labirinto, 
 */
public class Game implements Observable{

    private Maze maze; // Oggetto classe labirinto

    private ArrayList<Microrobot> microrobot; // Il microrobot che si muove nel labirinto

    private ArrayList<PositionSubcriber> subscribers; // Lista degli iscritti alla "newsletter" del gioco
    
    private Boolean firsMove; // Variabile per gestire il primo movimento del microrobot

    /**
     * Costruttore per prendere in input la difficolta' del labirinto passata come enumerazione
     * In base alla difficoltà scelta, crea il labirinto richiamando il Factory Method
     */
    public Game(Hardships h){
        this.maze = new MazeDifficulty().createMaze(h);
        assert maze != null; // Verifica che il labirinto sia stato creato
        this.microrobot = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.firsMove = true;
    }

    /**
     * Metodo per aggiungere un microrobot al gioco in base al numero passato come parametro
     * @param n
     */
    public void addMicrorobot(int n){
        for(int i = 0; i < n; i++){
            Position p = new Position(ThreadLocalRandom.current().nextInt(maze.getDim()- 1), ThreadLocalRandom.current().nextInt(maze.getDim() -1));
            this.microrobot.add(new Microrobot(p, new OneMoveState(new OneMove(maze.getMaze(), maze.getExitMaze()))));
        }
    }

    @Override
    /**
     * Metodo per aggiungere un iscritto alla "newsletter" del gioco
     * @param s
     */
    public void subscribe(PositionSubcriber subcriber){
        subscribers.add(subcriber);
    }

    @Override
    /**
     * Metodo per aggiornare tutti gli iscritti alla "newsletter" del gioco,
     * notificando loro la posizione e lo stato del microrobot, cambio di stato del labirinto e delle celle.
     */
    public void notifySubscribers(){
        for(PositionSubcriber s : subscribers){
            s.update(this.getMaze(), this.microrobot.getPosition(), this.microrobot.getMicroRobotState());
        }
    }

    /**
     * Metodo per restituire il labirinto
     */
    public Box[][] getMaze(){
        return this.maze.getMaze();
    }

    /**
     * Metodo per restituire la Posizione di uscita del labirinto
     */
    public Position getExitMaze(){
        return this.maze.getExitMaze();
    }
    
    /**
     * Metodo per calcolare la prossima mossa del microrobot
     */
}
