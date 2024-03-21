package com.maze.Bridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe per la connessione al database MySQL
 */
public class MysqlConnection implements IConnection{
    
    private final String URL; // URL del database
    private final String USER; // Username dell'account del database
    private final String PASSWORD; // Password dell'account del database

    private Connection connection; // Interfaccia per implementare la connessione al database

    /**
     * Costruttore inizializzare la connessione al database MySQL
     * @param url
     * @param user
     * @param password
     */
    public MysqlConnection(String url, String user, String password) {
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

    /**
     * Metodo per connettersi al database MySQL
     */
    @Override
    public void connect(){
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD); // Connessione al database
            System.out.println("Connected to the database");
        }
        catch(SQLException e){
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    /**
     * Metodo per disconnettersi dal database MySQL
     */
    @Override
    public void disconnect(){
        try{
            // Se la connessione non è nulla e non è chiusa
            if (connection != null && !connection.isClosed()) {
                connection.close(); // Chiusura della connessione
                System.out.println("Disconnected from the database");
            }
        }
        catch(SQLException e){
            System.err.println("Error disconnecting from the database: " + e.getMessage());
        }
    }

    /**
     * Metodo per eseguire un insert sul database MySQL
     * @param query
     * @return int
     * @throws SQLException
     */
    public int executeInsert(String query) {
        int result = 0; // Variabile per contenere il risultato dell'insert
        try {
            result = connection.createStatement().executeUpdate(query); // Esecuzione dell'insert
        } catch (SQLException e) {
            System.err.println("Error executing insert: " + e.getMessage());
        }
        return result; // Ritorno del risultato dell'insert
    }

    /**
     * Metodo per eseguire una query sul database MySQL
     * @param query
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null; // Variabile per contenere il risultato della query
        try {
            resultSet = connection.createStatement().executeQuery(query); // Esecuzione della query
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        return resultSet; // Ritorno del risultato della query
    }
}
