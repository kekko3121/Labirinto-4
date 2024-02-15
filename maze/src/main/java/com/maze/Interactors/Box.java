package com.maze.Interactors;

/**
 * Classe che rappresenta una casella del labirinto.
 * Una casella del labirinto è una casella con un valore e una posizione.
 */
public class Box {

        private ValueBox value; // valore della casella

        private Position position; // posizione della casella

        /**
         * Costruttore della classe per passare il valore e la posizione della casella.
         * @param value
         * @param position
         */
        public Box(ValueBox value, Position position){
            this.value = value;
            this.position = position;
        }
        
        /**
         * Restituisce il valore della casella
         * @return
         */
        public ValueBox getValue(){
            return value;
        }
        
        /**
         * Restituisce la posizione della casella
         * @return
         */
        public Position getPosition(){
            return position;
        }
        
        /**
         * imposta il valore della casella
         * @param value
         */
        public void setValue(ValueBox value){
            this.value = value;
        }
        
        /**
         * imposta la posizione della casella
         * @param position
         */
        public void setPosition(Position position){
            this.position = position;
        }
}