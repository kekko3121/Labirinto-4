package com.maze.Factory;

import java.util.Random;

import com.maze.Interactors.*;

/**
 * Classe astratta che rappresenta un labirinto.
 * Implementa l'interfaccia IMaze
 * @see IMaze
 */
public abstract class Maze implements IMaze {

    private Box [][] maze; // matrice di caselle che rappresenta il labirinto
    
    private int dim; // dimensione del labirinto

    private Position exitMaze; // posizione dell'uscita del labirinto

   /**
    * Costruttore della classe Maze che inizializza la matrice del labirinto con caselle vuote
    * @param dim
    */
    public Maze(int dim){
        maze = new Box[dim][dim]; // inizializza la matrice del labirinto
        for(int i = 0; i < dim; i++){ 
            for(int j = 0; j < dim; j++){
                maze[i][j] = new Box(ValueBox.EMPTY, new Position(i,j)); // inizializza la casella con valore EMPTY
            }
        }
        this.dim = dim; // inizializza la dimensione del labirinto
        generateExit(); // genera l'uscita del labirinto
    }

    /**
     * Ritorna il labirinto
     * @return
     */
    public Box[][] getMaze(){
        return maze;
    }

    /**
     * Ritorna la dimensione del labirinto
     * @return
     */
    public int getDim(){
        return dim;
    }

    /**
     * Genera l'uscita del labirinto
     */
    private void generateExit(){
        exitMaze = new Position(new Random().nextInt(dim), 0); // genera la posizione dell'uscita
    }

    /**
     * Genera le botole nel labirinto
     * @param ntrapdoors
     */
    protected void generateTrapdoors(int ntrapdoors){
        Random random = new Random();
        int trapdoorsPlaced = 0; // numero di botole piazzate
        while (trapdoorsPlaced < ntrapdoors) { // finchè non sono state piazzate tutte le botole
            int x = random.nextInt(dim); // genera una coordinata x casuale
            int y = random.nextInt(dim); // genera una coordinata y casuale
            // se la casella è vuota e non è l'uscita
            if (maze[x][y].getValue() == ValueBox.EMPTY && exitMaze.getX() != x && exitMaze.getY() != y){
                maze[x][y].setValue(ValueBox.HATCH); // piazza la botola
                trapdoorsPlaced++; // incrementa il numero di botole piazzate
            }
        }
    }

    /**
     * Ritorna la posizione dell'uscita del labirinto
     * @return
     */
    public Position getExitMaze(){
        return exitMaze;
    }

    /**
     * Ritorna la casella del labirinto in posizione x,y
     * @param x
     * @param y
     * @return
     */
    public Box getBox(int x, int y){
        return maze[x][y];
    
    }

    /**
     * Metodo astratto che genera il labirinto
     * Verrà implementato dalle classi figlie
     */
    public abstract void generateMaze();
}