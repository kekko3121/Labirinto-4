package com.maze.Interactors;

/**
 * Class that represents a box in the maze
 */
public class Box {
        
        /**
         * Value of the box
         */
        private ValueBox value;

        /**
         * Position of the box
         */
        private Position position;

        /**
         * Initializes a box with the given value and position
         * @param value of the box
         * @param position of the box
         */
        public Box(ValueBox value, Position position){
            this.value = value;
            this.position = position;
        }

        /**
         * Initializes a box with the given value
         * @param value of the box
         */
        public Box(ValueBox value){
            this.value = value;
        }
        
        /**
         * Returns the value of the box
         * @return
         */
        public ValueBox getValue(){
            return value;
        }
        
        /**
         * Returns the position of the box
         * @return
         */
        public Position getPosition(){
            return position;
        }
        
        /**
         * Sets the value of the box
         * @param value
         */
        public void setValue(ValueBox value){
            this.value = value;
        }
        
        /**
         * Sets the position of the box
         * @param position
         */
        public void setPosition(Position position){
            this.position = position;
        }    
}
