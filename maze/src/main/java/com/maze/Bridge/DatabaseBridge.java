package com.maze.Bridge;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseBridge {
    
    protected IConnection connection;

    public DatabaseBridge(IConnection connection) {
        this.connection = connection;
    }

    public abstract void connect();
    public abstract void disconnect();
    public abstract ResultSet executeQuery(String query) throws SQLException;
}
