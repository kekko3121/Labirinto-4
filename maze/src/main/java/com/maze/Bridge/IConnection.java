package com.maze.Bridge;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfaccia per implementare la connessione al database
 */
public interface IConnection {

    /**
     * Metodo per connettersi al database
     */
    public void connect();

    /**
     * Metodo per disconnettersi dal database
     */
    public void disconnect();

    /**
     * Metodo per eseguire un insert
     * @param query
     * @return
     * @throws SQLException
     */

    public int executeInsert(String query) throws SQLException;

    /**
     * Metodo per eseguire una query
     * @param query la query da eseguire
     * @return il risultato della query
     * @throws SQLException
     */
    public ResultSet executeQuery(String query) throws SQLException;
}
