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
    public void run(){
        List<List<Position>> solutions = new ArrayList<>(); // List to store solutions

        // Initialize the solutions
        for (int i = 0; i < NUM_BEES; i++) {
            solutions.add(new ArrayList<>(solutionTemplate)); // Add a copy of the template solution
        }

        // Circle of the ABC algorithm
        for (int cycle = 0; cycle < MAX_CYCLES; cycle++) {
            employedBeesPhase(solutions); // Employed bees phase
            onlookerBeesPhase(solutions); // Onlooker bees phase
        }
    }

    /**
     * calculate the quality of the solution
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
     */
    private void onlookerBeesPhase(List<List<Position>> solutions) {
        for (List<Position> solution : solutions) {
            double probability = calculateProbability(solution, solutions); // Calcola la probabilità della soluzione
            if (rand.nextDouble() < probability) {
                solution.clear(); // Cancella la soluzione se la probabilità è maggiore di un numero casuale
                solution.addAll(generateRandomSolution()); // Aggiunge una nuova soluzione casuale
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
     * calculate the probability of the solution
     * @param solution
     * @return
     */
    private double calculateProbability(List<Position> solution, List<List<Position>> allSolutions) {
        double quality = calculateQuality(solution); // Qualità della soluzione
        double totalQuality = 0.0; // Qualità totale delle soluzioni
        
        // Calcola la qualità totale delle soluzioni
        for (List<Position> sol : allSolutions) {
            totalQuality += calculateQuality(sol);
        }
        
        // Calcola e restituisce la probabilità della soluzione rispetto alle altre soluzioni
        return quality / totalQuality;
    }
}