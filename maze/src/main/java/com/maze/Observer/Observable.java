package com.maze.Observer;

/**
 * Interfaccia per implementare i metodi di subscribe e notify
 * appartenenti al pattern Observer
 */
public interface Observable {
    /**
     * Iscrive un oggetto PositionSubscriber alla "newsletter" dell' Observable
     */
    public void subscribe(PositionSubscriber subscriber);

    /**
     * Notifica tutti gli iscritti alla "newsletter" dell' Observable
     */
    public void notifySubscribers();
}