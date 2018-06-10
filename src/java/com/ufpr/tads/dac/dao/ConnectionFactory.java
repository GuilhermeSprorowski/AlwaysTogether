
package com.ufpr.tads.dac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String connectionName = "127.0.0.1";
    private int port = 3306;
    private String databaseName = "alwaystogether";
    private String dbUserName = "root";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            
        }
        Connection connect = DriverManager.getConnection("jdbc:mysql://" + connectionName
                + (port > 0 ? (":" + port + "/") : "") + databaseName + "?" + "user=" + dbUserName);

        return connect;
    }
}
