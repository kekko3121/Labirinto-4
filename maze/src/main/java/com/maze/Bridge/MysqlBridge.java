package com.maze.Bridge;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlBridge extends DatabaseBridge{
        
    public MysqlBridge(IConnection connection) {
        super(connection);
    }

    @Override
    public void connect() {
        connection.connect();
    }

    @Override
    public void disconnect() {
        connection.disconnect();
    }

    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        return connection.executeQuery(query);
    }
}
