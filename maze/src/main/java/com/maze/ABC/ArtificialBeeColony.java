package com.maze.ABC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;
import com.maze.Interactors.Box;

public class ArtificialBeeColony {
    private int MAX_CYCLES = 1000; // number of maximum cycles
    private int NUM_BEES = 40; // number of bees
    private int LIMIT = 50; // limit for the quality of the solution

    private Random rand; // random number generator
    private Position exitMaze; // position of the exit in the maze
    private Box[][] maze; // maze
    private int dim; // dimension of the maze
    private List<Position> solutionTemplate; // template solution for all bees

    /**
     * Initialize the Artificial Bee Colony algorithm with passed parameters
     * @param maze
     * @param exitMaze
     * @param dim
     */
    public ArtificialBeeColony(Box[][] maze, Position exitMaze, int dim) {
        this.maze = maze;
        this.exitMaze = exitMaze;
        this.dim = dim;
        this.rand = new Random();
        this.solutionTemplate = generateRandomSolution(); // generate a random solution
    }

    /**
     * Run the ABC algorithm
     */
    public List<Position> run(){
        List<List<Position>> solutions = new ArrayList<>(); // List to store solutions

        // Initialize the solutions
        for (int i = 0; i < NUM_BEES; i++) {
            solutions.add(new ArrayList<>(solutionTemplate)); // Add a copy of the template solution
        }
    
        // Circle of the ABC algorithm
        for (int cycle = 0; cycle < MAX_CYCLES; cycle++) {
            employedBeesPhase(solutions); // Employed bees phase
            onlookerBeesPhase(solutions); // Onlooker bees phase
            
            // Apply local improvement to each solution
            for (List<Position> solution : solutions) {
                improveSolution(solution);
            }
        }

        // Find the best solution
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

    /**
     * calculate the quality of the solution
     * @param solution
     */
    private void employedBeesPhase(List<List<Position>> solutions) {
        for (List<Position> solution : solutions) {
            double quality = calculateQuality(solution); // calculate the quality of the solution

            if (quality > LIMIT) {
                // Clear the solution and copy the template solution
                solution.clear();
                solution.addAll(solutionTemplate);
            }
        }
    }

    /**
     * calculate the probability of the solution
     * @param solutions
     */
    private void onlookerBeesPhase(List<List<Position>> solutions) {
        double[] probabilities = new double[NUM_BEES];
        double totalQuality = 0.0;
    
        // calculate the total quality of the solutions
        for (int i = 0; i < NUM_BEES; i++) {
            List<Position> solution = solutions.get(i);
            double quality = calculateQuality(solution);
            totalQuality += quality;
            probabilities[i] = quality;
        }
    
        // normalize the probabilities
        for (int i = 0; i < NUM_BEES; i++) {
            probabilities[i] /= totalQuality;
        }
    
       // select the solutions based on the probabilities
        for (int i = 0; i < NUM_BEES; i++) {
            if (rand.nextDouble() < probabilities[i]) {
                List<Position> solution = solutions.get(i);
                double quality = calculateQuality(solution);
                
                // generate a new solution
                List<Position> newSolution = generateRandomSolution();

                // calculate the quality of the new solution
                double newQuality = calculateQuality(newSolution);

                // replace the current solution with the new solution if the quality is better
                if (newQuality > quality) {
                    // Sostituisci la soluzione attuale con la nuova soluzione
                    solutions.set(i, newSolution);
                }
            }
        }
    }

    /**
     * generate a random solution for the ABC algorithm
     * @return a random solution
     */
    private List<Position> generateRandomSolution() {
        List<Position> solution = new ArrayList<>();
        Position current = new Position(0, 0);
        solution.add(current);

        int maxSteps = dim * dim * 2; // Definire un limite ragionevole
        int steps = 0;

        while (!current.equals(exitMaze) && steps < maxSteps) {
            int moveX = rand.nextInt(3) - 1;
            int moveY = rand.nextInt(3) - 1;

            Position next = new Position(current.getX() + moveX, current.getY() + moveY);

            if (isValidPosition(next)) {
                solution.add(next);
                current = next;
            }
            steps++;
        }

        return solution;
    }

    /**
     * verify if the position is not a wall
     * @param position
     * @return
     */
    private boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < dim &&
               position.getY() >= 0 && position.getY() < dim &&
               maze[position.getX()][position.getY()].getValue() != ValueBox.WALL;
    }

    /**
     * calculate the quality of the solution
     * @param solution
     * @return
     */
    private double calculateQuality(List<Position> solution) {
        Position current = solution.get(solution.size() - 1); // last position of the solution
        double distance = Math.sqrt(Math.pow(current.getX() - exitMaze.getX(), 2) + Math.pow(current.getY() - exitMaze.getY(), 2)); // distance from the exit
        return 1.0 / (1.0 + distance); // return quality of the solution
    }

    /**
     * Improve a solution using a local search strategy
     * @param solution the solution to be improved
     */
    private void improveSolution(List<Position> solution) {
        int improvementCount = 0; // Track the number of improvements
        int maxIterations = 100; // Define a maximum number of iterations for local search
        int iterations = 0;

        while (improvementCount < 3 && iterations < maxIterations) {
            // Clone the current solution for comparison
            List<Position> originalSolution = new ArrayList<>(solution);

            // Apply local search by randomly moving within a neighborhood
            for (int i = 1; i < solution.size() - 1; i++) {
                Position originalPosition = solution.get(i);

                // Generate random moves within a neighborhood
                for (int moveX = -1; moveX <= 1; moveX++) {
                    for (int moveY = -1; moveY <= 1; moveY++) {
                        Position newPosition = new Position(originalPosition.getX() + moveX, originalPosition.getY() + moveY);

                        // Check if the new position is valid and improves the solution
                        if (isValidPosition(newPosition)) {
                            solution.set(i, newPosition); // Move to the new position
                            double newQuality = calculateQuality(solution);

                            // If the quality improves, accept the move
                            if (newQuality > calculateQuality(originalSolution)) {
                                improvementCount++;
                            } else {
                                // Revert the move if the quality doesn't improve
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