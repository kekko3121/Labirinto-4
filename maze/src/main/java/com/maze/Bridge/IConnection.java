package com.maze.Bridge;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConnection {
    public void connect();
    public void disconnect();
    public ResultSet executeQuery(String query) throws SQLException;
}
