package com.maze.Factory;

import com.maze.Interactors.ValueBox;

/**
 * Classe che implementa il labirinto facile.
 * Il labirinto facile è un labirinto con dimensione 14x14 e con un numero di botole pari a 2.
 * @see Maze
 */
public class EasyMaze extends Maze{
    
    /**
     * Costruttore della classe per passare la dimensione del labirinto.
     */
    public EasyMaze(){
        super(14); // chiama il costruttore della superclasse per passare la dimensione del labirinto
    }
    
    /**
     * Metodo che genera il labirinto.
     */
    @Override
    public void generateMaze(){
        for(int i = getDim() - 1; i > 7;  i--){
            getBox(i, 7).setValue(ValueBox.WALL);
        }

        for(int i = 10; i < getDim() - 2; i++){
            getBox(10, i).setValue(ValueBox.WALL);
        }
        
        for(int i = 6; i < getDim(); i++){
            getBox(3, i).setValue(ValueBox.WALL);
        }
        generateTrapdoors(2); // genera 2 botole
    }
}
