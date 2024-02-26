package com.maze.Bridge;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe astratta per implementare il bridge con il database
 */
public abstract class DatabaseBridge {
    
    protected IConnection connection; // Interfaccia per implementare la connessione al database

    /**
     * Costruttore per passare la connessione al database utilizzato
     * @param connection
     */
    public DatabaseBridge(IConnection connection) {
        this.connection = connection;
    }


    /**
     * Classi astratte ridefinita nelle classi figlie
     */
    public abstract void connect();
    public abstract void disconnect();
    public abstract ResultSet executeQuery(String query) throws SQLException;
}
