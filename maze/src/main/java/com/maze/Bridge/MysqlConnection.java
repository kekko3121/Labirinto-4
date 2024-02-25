package com.maze.Bridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnection implements IConnection{
    
    private final String URL;
    private final String USER;
    private final String PASSWORD;

    private Connection connection;

    public MysqlConnection(String url, String user, String password) {
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

    @Override
    public void connect(){
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database");
        }
        catch(SQLException e){
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    @Override
    public void disconnect(){
        try{
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database");
            }
        }
        catch(SQLException e){
            System.err.println("Error disconnecting from the database: " + e.getMessage());
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        return resultSet;
    }
}
