package com.maze.Factory;

import com.maze.Interactors.ValueBox;

/**
 * Classe che implementa il labirinto difficile.
 * Il labirinto difficile è un labirinto con dimensione 14x14 e con un numero di botole pari a 7.
 * @see Maze
 */
public class HardMaze extends Maze{
    
    
    /**
     * Costruttore della classe per passare la dimensione del labirinto.
     */
    public HardMaze(){
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
        for(int i = 6; i < getDim() - 1; i++){
            getBox(3, i).setValue(ValueBox.WALL);
        }
        generateTrapdoors(7); // genera 7 botole
    }
}