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
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                Rectangle rect = new Rectangle(50, 50);

                if (isExternalWall(i, j, maze.length, exPosition)) {
                    rect.setFill(Color.GRAY); // Colore dei muri esterni
                    rect.setStroke(Color.BLACK); // Bordo nero
                    rect.setStrokeWidth(1.0);
                    
                    
                } else {
                    if (maze[i][j].getValue() == ValueBox.WALL) {
                        rect.setFill(Color.BLACK); // Parete interna
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
        return (row == 0 || row == size - 1 || col == 0 || col == size - 1) && !(exPosition.getX() == row && exPosition.getY() == col);
    }
}
