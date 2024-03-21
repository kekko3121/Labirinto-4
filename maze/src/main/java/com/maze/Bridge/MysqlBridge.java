package com.maze.Bridge;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe per estendere il bridge
 */
public class MysqlBridge extends DatabaseBridge{

    /**
     * Costruttore per passare la connessione al database utilizzato
     * @param connection
     */
    public MysqlBridge(IConnection connection) {
        super(connection);
    }

    /**
     * Metodo per connettersi al database
     */
    @Override
    public void connect() {
        connection.connect();
    }

    /**
     * Metodo per disconnettersi dal database
     */
    @Override
    public void disconnect() {
        connection.disconnect();
    }

    /**
     * Metodo per eseguire un insert
     * @param query
     * @return
     * @throws SQLException
     */
    @Override
    public int executeInsert(String query) throws SQLException {
        return connection.executeInsert(query);
    }

    /**
     * Metodo per eseguire una query
     * @param query
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        return connection.executeQuery(query);
    }
}
