package com.maze.ABC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;
import com.maze.Interactors.Box;

public class ArtificialBeeColony {

    private final int mazeSize; // Dimensione del labirinto
    private final Box[][] maze; // Labirinto
    private final Position exitPosition; // Posizione di uscita
    
    private final int MAX_ITERATIONS = 1000; // Numero massimo di iterazioni
    private final int MAX_TRIALS = 50; // Numero massimo di tentativi
    private final int NUM_BEES = 10; // Numero di api
    private final Random random = new Random(); // Generatore di numeri casuali

    /**
     * Costruttore della classe ArtificialBeeColony che inizializza il labirinto e la posizione di uscita
     * @param maze Labirinto
     * @param exitPosition Posizione di uscita
     */
    public ArtificialBeeColony(Box[][] maze, Position exitPosition) {
        this.mazeSize = maze.length;
        this.maze = maze;
        this.exitPosition = exitPosition;
    }

    /**
     * Metodo che calcola il percorso del microrobot all'interno del labirinto
     * @param initialPosition Posizione iniziale del microrobot
     * @return Lista di posizioni che rappresentano il percorso del microrobot
     */
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

    /**
     * Metodo che inizializza le api all'interno del labirinto
     * @param initialPosition Posizione iniziale delle api
     * @return Lista di api inizializzate
     */
    private List<Bee> initializeBees(Position initialPosition) {
        List<Bee> bees = new ArrayList<>();
        for (int i = 0; i < NUM_BEES; i++) {
            bees.add(new Bee(initialPosition, evaluateFitness(initialPosition)));
        }
        return bees;
    }

    /**
     * Metodo che invia le api impiegate all'interno del labirinto
     * @param bees Lista di api
     */
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

    /**
     * Metodo che invia le api osservatrici all'interno del labirinto
     * @param bees Lista di api
     */
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

    /**
     * Metodo che invia le api esploratrici all'interno del labirinto
     * @param bees Lista di api
     */
    private void sendScoutBees(List<Bee> bees) {
        for (Bee bee : bees) {
            if (bee.getTrials() >= MAX_TRIALS) {
                bee.setPosition(generateNewPosition(exitPosition));
                bee.resetTrials();
            }
        }
    }

    /**
     * Metodo che genera una nuova posizione all'interno del labirinto
     * @param currentPosition Posizione attuale
     * @return Nuova posizione
     */
    private Position generateNewPosition(Position currentPosition) {
        // Definisci gli spostamenti relativi consentiti
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // Scegli casualmente una delle quattro direzioni
        int[] move = moves[random.nextInt(moves.length)];
        
        // Calcola la nuova posizione
        int newX = currentPosition.getX() + move[0];
        int newY = currentPosition.getY() + move[1];
        
        // Verifica se la nuova posizione è all'interno del labirinto e non è un muro
        if (newX >= 0 && newX < mazeSize && newY >= 0 && newY < mazeSize && maze[newX][newY].getValue() != ValueBox.WALL) {
            return new Position(newX, newY);
        } else {
            // Se la nuova posizione è fuori dal labirinto o è un muro, resta nella posizione attuale
            return currentPosition;
        }
    }

    /**
     * Metodo che calcola il valore di fitness di una posizione all'interno del labirinto
     * @param position Posizione all'interno del labirinto
     * @return Valore di fitness
     */
    private double evaluateFitness(Position position) {
        double distance = Math.sqrt(Math.pow(position.getX() - exitPosition.getX(), 2) +
                                    Math.pow(position.getY() - exitPosition.getY(), 2));
        return 1.0 / (1.0 + distance);
    }

    /**
     * Metodo che restituisce l'api con il valore di fitness più alto
     * @param bees Lista di api
     * @return Api con il valore di fitness più alto
     */
    private Bee getBestBee(List<Bee> bees) {
        Bee bestBee = bees.get(0);
        for (Bee bee : bees) {
            if (bee.getFitness() > bestBee.getFitness()) {
                bestBee = bee;
            }
        }
        return bestBee;
    }

    /**
     * Classe interna che rappresenta un ape all'interno del labirinto
     */
    private static class Bee {
        private Position position; // Posizione dell ape
        private double fitness; // Valore di fitness dell'ape
        private int trials; // Numero di tentativi

        /**
         * Costruttore della classe Bee
         * @param position Posizione dell'ape
         * @param fitness Valore di fitness dell'ape
         */
        public Bee(Position position, double fitness) {
            this.position = position;
            this.fitness = fitness;
        }

        /**
         * Metodo che restituisce la posizione dell'ape
         * @return Posizione dell'ape
         */
        public Position getPosition() {
            return position;
        }

        /**
         * Metodo che imposta la posizione dell'ape
         * @param position Posizione dell'ape
         */
        public void setPosition(Position position) {
            this.position = position;
        }

        /**
         * Metodo che restituisce il valore di fitness dell'ape
         * @return Valore di fitness dell'ape
         */
        public double getFitness() {
            return fitness;
        }

        /**
         * Metodo che imposta il valore di fitness dell'ape
         * @param fitness Valore di fitness dell'ape
         */
        public void setFitness(double fitness) {
            this.fitness = fitness;
        }

        /**
         * Metodo che restituisce il numero di tentativi dell'ape
         * @return Numero di tentativi dell'ape
         */
        public int getTrials() {
            return trials;
        }

        /**
         * Metodo che reimposta il numero di tentativi dell'ape
         */
        public void resetTrials() {
            trials = 0;
        }

        /**
         * Metodo che incrementa il numero di tentativi dell'ape
         */
        public void incrementTrials() {
            trials++;
        }
    }
}