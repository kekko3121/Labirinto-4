package com.maze.Observer;

/**
 * Interfaccia per la gestione degli osservatori all'interno del gioco
 * @param observer
 */
public interface Observable{
    /**
     * Metodo per iscrivere un osservatore alla newsletter
     * @param observer
     */
    public void subscribe(MicrorobotPosition observer);
    
    /**
     * Metodo per notificare tutti gli osservatori iscritti alla newsletter
     */
    public void notifyObservers();
}