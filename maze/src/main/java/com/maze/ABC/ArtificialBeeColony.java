package com.maze.ABC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;
import com.maze.Interactors.Box;

public class ArtificialBeeColony {

    private final int mazeSize;
    private final Box[][] maze;
    private final Position exitPosition;
    
    private final int MAX_ITERATIONS = 1000;
    private final int MAX_TRIALS = 50;
    private final int NUM_BEES = 10;
    private final Random random = new Random();

    public ArtificialBeeColony(Box[][] maze, Position exitPosition) {
        this.mazeSize = maze.length;
        this.maze = maze;
        this.exitPosition = exitPosition;
    }

    public List<Position> calculateMicrorobotPath(Position initialPosition) {
        List<Position> pathToExit = new ArrayList<>();
        pathToExit.add(initialPosition); // Aggiungi la posizione iniziale al percorso
    
        List<Bee> bees = initializeBees(initialPosition);
        Bee bestBee = getBestBee(bees);
    
        int iterations = 0;
        int trials = 0;
        while (iterations < MAX_ITERATIONS && trials < MAX_TRIALS) {
            sendEmployedBees(bees);
            sendOnlookerBees(bees);
            bestBee = getBestBee(bees);
            if (bestBee.getFitness() == 1.0) { // Microrobot ha raggiunto l'uscita
                pathToExit.add(bestBee.getPosition()); // Aggiungi la posizione di uscita al percorso
                break;
            }
            sendScoutBees(bees);
            iterations++;
            trials = bestBee.getTrials();
            pathToExit.add(bestBee.getPosition()); // Aggiungi la nuova posizione al percorso
        }
    
        return pathToExit;
    }    

    private List<Bee> initializeBees(Position initialPosition) {
        List<Bee> bees = new ArrayList<>();
        for (int i = 0; i < NUM_BEES; i++) {
            bees.add(new Bee(initialPosition, evaluateFitness(initialPosition)));
        }
        return bees;
    }

    private void sendEmployedBees(List<Bee> bees) {
        for (Bee bee : bees) {
            Position newPosition = generateNewPosition(bee.getPosition());
            double newFitness = evaluateFitness(newPosition);
            if (newFitness > bee.getFitness()) {
                bee.setPosition(newPosition);
                bee.setFitness(newFitness);
                bee.resetTrials();
            } else {
                bee.incrementTrials();
            }
        }
    }

    private void sendOnlookerBees(List<Bee> bees) {
        double totalFitness = 0.0;
        for (Bee bee : bees) {
            totalFitness += bee.getFitness();
        }
        for (Bee bee : bees) {
            double probability = bee.getFitness() / totalFitness;
            if (random.nextDouble() < probability) {
                Position newPosition = generateNewPosition(bee.getPosition());
                double newFitness = evaluateFitness(newPosition);
                if (newFitness > bee.getFitness()) {
                    bee.setPosition(newPosition);
                    bee.setFitness(newFitness);
                    bee.resetTrials();
                } else {
                    bee.incrementTrials();
                }
            }
        }
    }

    private void sendScoutBees(List<Bee> bees) {
        for (Bee bee : bees) {
            if (bee.getTrials() >= MAX_TRIALS) {
                bee.setPosition(generateRandomPosition());
                bee.resetTrials();
            }
        }
    }

    private Position generateNewPosition(Position currentPosition) {
        List<Position> possibleMoves = new ArrayList<>();
        // Definisci gli spostamenti relativi consentiti (ignorando la posizione attuale)
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] move : moves) {
            int newX = currentPosition.getX() + move[0];
            int newY = currentPosition.getY() + move[1];
            // Verifica se la nuova posizione è all'interno del labirinto e non è un muro
            if (newX >= 0 && newX < mazeSize && newY >= 0 && newY < mazeSize && maze[newX][newY].getValue() != ValueBox.WALL) {
                possibleMoves.add(new Position(newX, newY));
            }
        }
        if (!possibleMoves.isEmpty()) {
            // Scegli casualmente una delle mosse possibili
            return possibleMoves.get(random.nextInt(possibleMoves.size()));
        } else {
            // Se non ci sono mosse valide, resta nella posizione attuale
            return currentPosition;
        }
    }    

    private double evaluateFitness(Position position) {
        double distance = Math.sqrt(Math.pow(position.getX() - exitPosition.getX(), 2) +
                                    Math.pow(position.getY() - exitPosition.getY(), 2));
        return 1.0 / (1.0 + distance);
    }

    private Bee getBestBee(List<Bee> bees) {
        Bee bestBee = bees.get(0);
        for (Bee bee : bees) {
            if (bee.getFitness() > bestBee.getFitness()) {
                bestBee = bee;
            }
        }
        return bestBee;
    }

    private Position generateRandomPosition() {
        int x = random.nextInt(mazeSize);
        int y = random.nextInt(mazeSize);
        return new Position(x, y);
    }

    private static class Bee {
        private Position position;
        private double fitness;
        private int trials;

        public Bee(Position position, double fitness) {
            this.position = position;
            this.fitness = fitness;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public double getFitness() {
            return fitness;
        }

        public void setFitness(double fitness) {
            this.fitness = fitness;
        }

        public int getTrials() {
            return trials;
        }

        public void resetTrials() {
            trials = 0;
        }

        public void incrementTrials() {
            trials++;
        }
    }
}