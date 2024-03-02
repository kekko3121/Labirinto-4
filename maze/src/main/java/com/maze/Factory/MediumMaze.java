package com.maze.Factory;

import com.maze.Interactors.ValueBox;

/**
 * Classe che implementa il labirinto medio.
 * Il labirinto medio è un labirinto con dimensione 14x14 e con un numero di botole pari a 4.
 * @see Maze
 */
public class MediumMaze extends Maze{
    
    /**
     * Costruttore della classe per passare la dimensione del labirinto.
     */
    public MediumMaze(){
        super(14); // call the constructor of the Maze class with the dimension of 14
    }

    /**
     * Metodo che genera il labirinto.
     */
    public void generateMaze(){
        for(int i = getDim() - 1; i > 7;  i--){
            getBox(i, 7).setValue(ValueBox.WALL);
        }
        for(int i = 10; i < getDim() -2; i++){
            getBox(10, i).setValue(ValueBox.WALL);
        }
        for(int i = 6; i < getDim(); i++){
            getBox(3, i).setValue(ValueBox.WALL);
        }
        generateTrapdoors(4); // genera 4 botole
    }
}