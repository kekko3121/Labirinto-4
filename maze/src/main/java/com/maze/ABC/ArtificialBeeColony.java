package com.maze.ABC;

import com.maze.Interactors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtificialBeeColony {
    private int MAX_CYCLES = 500; // Numero massimo di cicli
    private int NUM_BEES = 20; // Numero di "api"
    private int LIMIT = 50; // Limite per la qualità della soluzione

    private Random rand; // Generatore di numeri casuali
    private Position exitMaze; // Posizione dell'uscita nel labirinto
    private Box[][] maze; // Labirinto
    private List<Position> solutionTemplate; // Modello di soluzione per tutte le "api"

    public ArtificialBeeColony(Box[][] maze, Position exitMaze) {
        this.maze = maze;
        this.exitMaze = exitMaze;
        this.rand = new Random();
        this.solutionTemplate = generateRandomSolution();
    }

    public List<Position> run() {
        List<List<Position>> solutions = new ArrayList<>();

        for (int i = 0; i < NUM_BEES; i++) {
            solutions.add(new ArrayList<>(solutionTemplate));
        }

        for (int cycle = 0; cycle < MAX_CYCLES; cycle++) {
            employedBeesPhase(solutions);
            onlookerBeesPhase(solutions);

            for (List<Position> solution : solutions) {
                improveSolution(solution);
            }
        }

        List<Position> bestSolution = solutions.get(0);
        double bestQuality = calculateQuality(bestSolution);
        for (List<Position> solution : solutions) {
            double quality = calculateQuality(solution);
            if (quality > bestQuality) {
                bestSolution = solution;
                bestQuality = quality;
            }
        }

        return bestSolution;
    }

    private void employedBeesPhase(List<List<Position>> solutions) {
        for (List<Position> solution : solutions) {
            double quality = calculateQuality(solution);

            if (quality > LIMIT) {
                solution.clear();
                solution.addAll(solutionTemplate);
            }
        }
    }

    private void onlookerBeesPhase(List<List<Position>> solutions) {
        double[] probabilities = new double[NUM_BEES];
        double totalQuality = 0.0;

        for (int i = 0; i < NUM_BEES; i++) {
            List<Position> solution = solutions.get(i);
            double quality = calculateQuality(solution);
            totalQuality += quality;
            probabilities[i] = quality;
        }

        for (int i = 0; i < NUM_BEES; i++) {
            probabilities[i] /= totalQuality;
        }

        for (int i = 0; i < NUM_BEES; i++) {
            if (rand.nextDouble() < probabilities[i]) {
                List<Position> solution = solutions.get(i);
                double quality = calculateQuality(solution);

                List<Position> newSolution = generateRandomSolution();
                double newQuality = calculateQuality(newSolution);

                if (newQuality > quality) {
                    solutions.set(i, newSolution);
                }
            }
        }
    }

    private List<Position> generateRandomSolution() {
        List<Position> solution = new ArrayList<>();
        Position current = new Position(0, 0);
        solution.add(current);
    
        while (!current.equals(exitMaze) && isValidMovePossible(solution)) {
            int moveX = rand.nextInt(3) - 1;
            int moveY = rand.nextInt(3) - 1;
    
            Position next = new Position(current.getX() + moveX, current.getY() + moveY);
    
            if (isValidPosition(next)) {
                solution.add(next);
                current = next;
            }
        }
    
        return solution;
    }
    
    private boolean isValidMovePossible(List<Position> solution) {
        // Controlla se ci sono ancora mosse possibili nel labirinto
        int remainingMoves = maze.length * maze[0].length * 2 - solution.size() + 1;
        return remainingMoves > 0;
    }

    private boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < maze.length &&
                position.getY() >= 0 && position.getY() < maze[0].length &&
                maze[position.getX()][position.getY()].getValue() != ValueBox.WALL;
    }

    private double calculateQuality(List<Position> solution) {
        Position current = solution.get(solution.size() - 1);
        double distance = Math.sqrt(Math.pow(current.getX() - exitMaze.getX(), 2) +
                Math.pow(current.getY() - exitMaze.getY(), 2));
        return 1.0 / (1.0 + distance);
    }

    private void improveSolution(List<Position> solution) {
        int improvementCount = 0;
        int maxIterations = 100;
        int iterations = 0;

        while (improvementCount < 3 && iterations < maxIterations) {
            List<Position> originalSolution = new ArrayList<>(solution);

            for (int i = 1; i < solution.size() - 1; i++) {
                Position originalPosition = solution.get(i);

                for (int moveX = -1; moveX <= 1; moveX++) {
                    for (int moveY = -1; moveY <= 1; moveY++) {
                        Position newPosition = new Position(originalPosition.getX() + moveX,
                                originalPosition.getY() + moveY);

                        if (isValidPosition(newPosition)) {
                            solution.set(i, newPosition);
                            double newQuality = calculateQuality(solution);

                            if (newQuality > calculateQuality(originalSolution)) {
                                improvementCount++;
                            } else {
                                solution.set(i, originalPosition);
                            }
                        }
                    }
                }
            }

            iterations++;
        }
    }
}