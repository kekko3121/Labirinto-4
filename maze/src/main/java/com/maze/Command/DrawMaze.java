package com.maze.Command;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import com.maze.Interactors.Box;
import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;

import javafx.scene.layout.GridPane;

public class DrawMaze extends MazeCommand {
    
    private GridPane mazeGrid;

    public DrawMaze(String name, String surname, String nickname, GridPane mazeGrid) {
        super(name, surname, nickname);
        this.mazeGrid = mazeGrid;
    }

    public void buildMaze(Box[][] maze, Position exPosition) {
        int size = maze.length;
    
        for (int i = 0; i <= size + 1; i++) { // Estendi i limiti per includere i muri esterni
            for (int j = 0; j <= size + 1; j++) {
                Rectangle rect = new Rectangle(50, 50);
    
                if (isExternalWall(i, j, size, exPosition)) {
                    rect.setFill(Color.GRAY); // Colore dei muri esterni
                    rect.setStroke(Color.BLACK); // Bordo nero
                    rect.setStrokeWidth(1.0);
                } else if (i == exPosition.getX() + 1 && j == exPosition.getY() + 1) { // Verifica se siamo nella posizione dell'uscita
                    rect.setFill(Color.WHITE); // Lascia vuota l'uscita
                    rect.setStroke(Color.BLACK); // Bordo nero
                    rect.setStrokeWidth(1.0);
                } else if (i > 0 && i <= size && j > 0 && j <= size) { // Assicurati di disegnare solo il labirinto interno
                    if (maze[i - 1][j - 1].getValue() == ValueBox.WALL) {
                        rect.setFill(Color.BLACK); // Parete interna
                        rect.setStroke(Color.BLACK); // Bordo nero
                        rect.setStrokeWidth(1.0);
                    } else if (maze[i - 1][j - 1].getValue() == ValueBox.HATCH) {
                        rect.setFill(Color.BLUE);
                        rect.setStroke(Color.BLACK); // Bordo nero
                        rect.setStrokeWidth(1.0);
                    } else {
                        rect.setFill(Color.WHITE); // Passaggio
                        rect.setStroke(Color.BLACK); // Bordo nero
                        rect.setStrokeWidth(1.0);
                    }
                }
    
                mazeGrid.add(rect, j, i);
            }
        }
    }
    
    private boolean isExternalWall(int row, int col, int size, Position exPosition) {
        return (row == 0 || row == size + 1 || col == 0 || col == size + 1)
                && !(row == exPosition.getX() && col == exPosition.getY()); // Non aggiungere 1 qui, poiché l'uscita è inclusa nei limiti della matrice
    }
}
